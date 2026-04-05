/* 
 * PROJECT I: Circle.java
 *
 * This file contains a template for the class Circle. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Point class.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class Circle {
    private double r;
    private Point A;
    public static final double GEOMTOL = 1.0e-6;


    /*
     * Here, you should define private variables that represent the radius and
     * centre of this particular Circle. The radius should be of type double,
     * and the centre should be of type Point.
     */
    

    // =========================
    // Constructors
    // =========================
    /**
     * Default constructor - performs no initialization.
     */
    public Circle() {
        // This method is complete.
    }
    
    /**
     * Alternative constructor, which sets the circle up with x and y
     * co-ordinates representing the centre, and a radius. Remember you should
     * not store these x and y co-ordinates explicitly, but instead create a
     * Point to hold them for you.
     *
     * @param xc   x-coordinate of the centre of the circle
     * @param yc   y-coordinate of the centre of the circle
     * @param rad  radius of the circle
     */
    public Circle(double xc, double yc, double rad) {
        A = new Point(xc,yc);
        r=rad;

        
        // You need to fill in this method.
    }

    /**
     * Alternative constructor, which sets the circle up with a Point
     * representing the centre, and a radius.
     *
     * @param center  Point representing the centre
     * @param rad     Radius of the circle
     */
    
    public Circle(Point center, double rad) {
        A = center;
        r = rad;
        
        // You need to fill in this method.
    }

    // =========================
    // Setters and Getters
    // =========================

    /**
     * Setter - sets the co-ordinates of the centre.
     *
     * @param xc  new x-coordinate of the centre
     * @param yc  new y-coordinate of the centre
     */   
    public void setCentre(double xc, double yc) {
        A = new Point(xc,yc);
        // You need to fill in this method.
    }

    /**
     * Setter - sets the centre of the circle to a new Point.
     *
     * @param C  A Point representing the new centre of the circle.
     */   
    public void setCentre(Point A) {
        this.A = A;
        // You need to fill in this method.
    }
    
    /**
     * Setter - change the radius of this circle.
     *
     * @param rad  New radius for the circle.
     */   
    public void setRadius(double rad) {
        this.r = rad;
        // You need to fill in this method.
    }
    
    /**
     * Getter - returns the centre of this circle.
     *
     * @return The centre of this circle (a Point).
     */   
    public Point getCentre(){
        return A;
        // You need to fill in this method.
    }

    /**
     * Getter - extract the radius of this circle.
     *
     * @return The radius of this circle.
     */   
    public double getRadius(){
        return r;
        // You need to fill in this method.
    }

    // =========================
    // Convertors
    // =========================

    /**
     * Calculates a String representation of the Circle.
     *
     * @return A String of the form: "[Ax,Ay], r=Radius" where Ax and Ay are
     *         numerical values of the coordinates, and Radius is a numerical
     *         value of the radius.
     */
    public String toString() {
        return "[" + A.getX() + "," + A.getY() + "], " + "r = " + r;
        // You need to fill in this method.
    }
    
    // ==========================
    // Service routines
    // ==========================
    
    /**
     * Similar to the equals() function in Point. Returns true if two Circles
     * are equal. By this we mean:
     * 
     * - They have the same radius (up to tolerance).
     * - They have the same centre (up to tolerance).
     * 
     * Remember that the second test is already done in the Point class!
     *
     * @return true if the two circles are equal.
     */
    public boolean equals(Circle c) {
        return (Math.abs(r-c.getRadius()) <= GEOMTOL && A.equals(c.getCentre()) == true);

        

        // You need to fill in this method.
    }
    
    // -----------------------------------------------------------------------
    // Do not change the method below! It is essential for marking the
    // project, and you may lose marks if it is changed.
    // -----------------------------------------------------------------------

    /**
     * Compare this Circle with some Object, using the test above.
     *
     * @param obj  The object to compare with.
     * @return true if the two objects are equal.
     */
    public boolean equals(Object obj) {
        // This method is complete.
        
        if (obj instanceof Circle) {
            boolean test = false;
            Circle C = (Circle)obj;
            
            test = this.equals(C);

            return test;
        } else {
            return false;
        }
    }

    // ======================================
    // Implementors
    // ======================================
    
    /**
     * Computes and returns the area of the circle.
     *
     * @return  Area of the circle
     */
    public double area(){
        return Math.PI*Math.pow(r,2);
        
        // You need to fill in this method.
    }

    // =======================================================
    // Tester - test methods defined in this class
    // =======================================================
    
    public static void main(String args[]) {
        // You can use this method for testing.
        Circle a = new Circle(3,0,2);
        Circle b = new Circle(4,1,5);
        Circle c = new Circle(-1,3,2);
        double d = a.area();
        
        System.out.println("The area of " + a + " is " + d);
        System.out.println("The centre of b is " + b.getCentre());
        System.out.println("The radius of c is " + c.getRadius());


    }
}
