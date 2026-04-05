/*
 * PROJECT I: Project1.java
 *
 * As in project 0, this file - and the others you downloaded - form a
 * template which should be modified to be fully functional.
 *
 * This file is the *last* file you should implement, as it depends on both
 * Point and Circle. Thus your tasks are to:
 *
 * 1) Make sure you have carefully read the project formulation. It contains
 *    the descriptions of all of the functions and variables below.
 * 2) Write the class Point.
 * 3) Write the class Circle
 * 4) Write this class, Project1. The results() method will perform the tasks
 *    laid out in the project formulation.
 */

import java.util.Scanner;
import java.io.*;

public class Project1 {
    // -----------------------------------------------------------------------
    // Do not modify the names of the variables below! This is where you will
    // store the results generated in the Results() function.
    // -----------------------------------------------------------------------
    public int      circleCounter; // Number of non-singular circles in the file.
    public double[] aabb;          // The bounding rectangle for the first and 
                                   // last circles
    public double   maxArea;       // Area of the largest circle (by area).
    public double   minArea;       // Area of the smallest circle (by area).
    public double   averageArea;   // Average area of the circles.
    public double   stdArea;       // Standard deviation of area of the circles.
    public double   medArea;       // Median of the area.
    public int      stamp=210207;
    // -----------------------------------------------------------------------
    // You may implement - but *not* change the names or parameters of - the
    // functions below.
    // -----------------------------------------------------------------------
    public static final double GEOMTOL = 1.0e-6;
    Circle [] circles;             // Array of the circles.
    double [] areas;               // Array of the areas of the circles. 
    double [] xcord_max;           // Array of xc + rad of each circle. 
    double [] xcord_min;           // Array of xc - rad of each circle.
    double [] ycord_max;           // Array of yc + rad of each circle.
    double [] ycord_min;           // Array of yc - rad of each circle.
    Circle C_first;                // First circle of the data file.
    Circle C_last;                 // Last circle of the data file.
    int numC = 1;                  // Counter of non-singular circles 
    
    /**
     * Default constructor for Project1. You should leave it empty.
     */
    public Project1() {

    }
        // This method is complete.
    
    /**
     * Results function. It should open the file called FileName (using
     * Scanner), and from it generate the statistics outlined in the project
     * formulation. These are then placed in the instance variables above.
     *
     * @param fileName  The name of the file containing the circle data.
     */
    public void results(String fileName){
        
        double x,y,rad;

        try {
          Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
          while(scanner.hasNext()) {
          
            //Read the three values on each line of the file
            x = scanner.nextDouble();
            y = scanner.nextDouble();
            rad = scanner.nextDouble();
            if(rad>GEOMTOL){        // rad bigger than some precision
                circleCounter ++;
            }
 
          }

          Scanner scanner2 = new Scanner(new BufferedReader(new FileReader(fileName)));

          circles = new Circle[circleCounter];        // array to store non-singular circles
          areas = new double[circleCounter];          // array to store area of non-singular circles
        
          while(scanner2.hasNext()) {
            
            //Read the three values on each line of the file
            x = scanner2.nextDouble();
            y = scanner2.nextDouble();
            rad = scanner2.nextDouble();
            
            if(rad>GEOMTOL){
              if (numC==1){
                C_first = new Circle(x,y,rad);               // stores first circle of file
              }
              if (numC==circleCounter){
                C_last= new Circle(x,y,rad);                 // stores last circle of file
              }

              circles[numC-1] = new Circle(x,y,rad);         // stores circles in array
              areas[numC-1] = new Circle(x,y,rad).area();    // stores areas in array
              numC += 1; 
            }
          
            
                 
          }
          Circle [] b_circ = new Circle[]{C_first,C_last};   // array of first and last circle
          aabb = AABB(b_circ);                               // defines rectagle boundering first and last circle

          // Areas of largest and smallest circle
          maxArea = getMax(areas);
          minArea = getMin(areas);

          // Average area and standard deviation of circles
          averageArea = averageCircleArea(circles);
          stdArea = areaStandardDeviation(circles);

          // Sorts areas array and gets median circle
          insertionsort(areas);
          medArea = getMedian(areas);


        } catch(Exception e) {
          System.err.println("An error has occured. See below for details");
          e.printStackTrace();
        }
        
    }
    
    
    /**
     * A function to calculate the avarage area of circles in the array provided. 
     *
     * @param circles  An array if Circles
     */

    public double averageCircleArea(Circle[] circles) {
      double sum_areas = 0;
      for (int i = 0; i<=circles.length-1;i++){
        sum_areas += areas[i];
      averageArea = sum_areas/circles.length;
      }

      return averageArea;
    }
    
    /**
     * A function to calculate the standart deviation of areas in the circles in the array provided. 
     *
     * @param circles  An array of Circles
     */

    public double areaStandardDeviation(Circle[] circles) {
      double avg_std = 0;
      for (int i = 0; i<=circles.length-1;i++){
        avg_std = Math.pow(areas[i]-averageArea,2);
      stdArea = Math.pow(avg_std/(circles.length-1),0.5);
      }
      return stdArea;
    }

   /**
     * A function to calculate the maximum value in the array provided.
     *
     * @param arr  An array of doubles
     */

    public double getMax(double[] arr){ 
      double maxValue = arr[0]; 
      for(int i=0;i < arr.length;i++){ 
        if(arr[i] > maxValue){ 
          maxValue = arr[i]; 
        } 
      } 
      return maxValue; 
    }

  /**
     * A function to calculate the minimum value in the array provided.
     *
     * @param arr  An array of doubles
     */

    public double getMin(double[] arr){ 
      double minValue = arr[0]; 
      for(int i=0;i<arr.length;i++){ 
        if(arr[i] < minValue){ 
          minValue = arr[i]; 
        } 
      } 
      return minValue; 
    } 

      /**
     * A function to sort the array provided. (WEEK 16 SLIDES LECTURE 6 MA117)
     *
     * @param arr  An array of doubles
     */

    public void insertionsort(double[] arr){
    
      for(int i=1; i<arr.length; i++) {
        double current = arr[i];
        int j = i-1;
        while(j>=0 && current <= arr[j]) {
          arr[j+1] = arr[j];
          j = j-1;
        }
        arr[j+1] = current;
      }
    }

     /**
     * A function to get the median of an array. 
     *
     * @param arr  An array of doubles
     */
    
    public double getMedian(double[] arr){
      if (arr.length%2==0){
        medArea =  Double.valueOf((arr[(arr.length)/2-1]+arr[arr.length/2])/2);
      }
      if (arr.length%2==1){
        medArea = Double.valueOf(arr[(arr.length-1)/2]);
      }
      return medArea;
    }
    /**
     * Returns 4 values in an array [X,Y,W,H] that define the rectangle
     * that surrounds the array of circles given, as set out in the 
     * project formulation.
     *
     * @param circles  An array of Circles
     * @return An array of doubles [X1,Y1,X2,Y2] that define the bounding rectangle with
     *         the origin (bottom left) at [X1,Y1] and opposite corner (top right)
     *         at [X2,Y2]
     */
    public double[] AABB(Circle[] circles){
      double X1,Y1,X2,Y2;
      xcord_max = new double[circles.length];
      xcord_min = new double[circles.length];
      ycord_max = new double[circles.length];
      ycord_min = new double[circles.length];

      for(int i = 0; i<circles.length;i++){
        xcord_max[i] = circles[i].getCentre().getX()+circles[i].getRadius();
        xcord_min[i] = circles[i].getCentre().getX()-circles[i].getRadius();
        ycord_max[i] = circles[i].getCentre().getY()+circles[i].getRadius();
        ycord_min[i] = circles[i].getCentre().getY()-circles[i].getRadius();
      }
      X1 = getMin(xcord_min);
      X2 = getMax(xcord_max);
      Y1 = getMin(ycord_min);
      Y2 = getMax(ycord_max);
      
        return new double[]{X1,Y1,X2,Y2};
    }

  
    // =======================================================
    // Tester - tests methods defined in this class
    // =======================================================

    /**
     * Your tester function should go here (see week 14 lecture notes if
     * you're confused). It is not tested by BOSS, but you will receive extra
     * credit if it is implemented in a sensible fashion.
     */
    public static void main(String args[]){
      
        Project1 a = new Project1();
          a.results("Project1.data");
          System.out.println("The number of non-singular circles is " + a.circleCounter);
          System.out.println("The maximum area is " + a.maxArea);
          System.out.println("The minimum area is " + a.minArea);
          System.out.println("The average area is " + a.averageArea);
          System.out.println("The standard deviation is " + a.stdArea);
          System.out.println("The median area is " + a.medArea);
          System.out.println("The coordinates of the bounding rectangle for the first and last circle are: (" + a.aabb[0] + ","
                                                                                                              + a.aabb[1] + ") and (" 
                                                                                                              + a.aabb[2]+ ","
                                                                                                              + a.aabb[3]+")");
    }
}