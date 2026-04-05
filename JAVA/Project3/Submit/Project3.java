/*
 * PROJECT III: Project3.java
 *
 * This file contains a template for the class Project3. None of methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Matrix class, as well as GeneralMatrix and TriMatrix.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class Project3 {
    /**
     * Calculates the variance of the distribution defined by the determinant
     * of a random matrix. See the formulation for a detailed description.
     *
     * @param m           The matrix object that will be filled with random
     *                    samples.
     * @param numSamples  The number of samples to generate when calculating 
     *                    the variance. 
     * @return            The variance of the distribution.
     */
    public static double matVariance(Matrix m, int numSamples) {

        // Creates 3 variables: determinant, sum of determinants and sum of the squares of the determinants
        double det = 0;
        double sumdet = 0;
        double sumdet2 = 0;
        // For each iteration, calculates the determinant of the random matrix m and calculates the variance of the determinants
        for(int i=1;i<=numSamples;i++){
            m.random();
            det = m.determinant();
            sumdet = sumdet + det;
            sumdet2 = sumdet2 + Math.pow(det,2);
        }
        return (sumdet2/numSamples-Math.pow(sumdet/numSamples,2));
        // You need to fill in this method.(Completed)
    }
    
    /**
     * This function should calculate the variances of matrices for matrices
     * of size 2 <= n <= 50. See the formulation for more detail.
     */
    public static void main(String[] args) {
        GeneralMatrix gm;
        TriMatrix tm;
        for(int n=2;n<51;n++){
            gm = new GeneralMatrix(n,n);
            tm = new TriMatrix(n);
            System.out.println(n+" "+matVariance(gm,15000)+" "+matVariance(tm,150000));
        }
        // You need to fill in this method.(Completed)
    }
}