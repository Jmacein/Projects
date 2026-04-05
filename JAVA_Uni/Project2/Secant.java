/*
 * PROJECT II: Secant.java
 *
 * This file contains a template for the class Secant. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * In this class, you will create a basic Java object responsible for
 * performing the Secant root finding method on a given polynomial
 * f(z) with complex co-efficients. The formulation outlines the method, as
 * well as the basic class structure, details of the instance variables and
 * how the class should operate.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file! You should also test this class using the main()
 * function.
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class Secant {
    /**
     * The maximum number of iterations that should be used when applying
     * Secant. Ensure this is *small* (e.g. at most 50) otherwise your
     * program may appear to freeze!
     */
    public static final int MAXITER = 20;

    /**
     * The tolerance that should be used throughout this project. Note that
     * you should reference this variable and _not_ explicity write out
     * 1.0e-10 in your solution code. Other classes can access this tolerance
     * by using Secant.TOL.
     */
    public static final double TOL = 1.0e-10;

    /**
     * The polynomial we wish to apply the Secant method to.
     */
    private Polynomial f;


    /**
     * A root of the polynomial f corresponding to the root found by the
     * iterate() function below.
     */
    private Complex root;
    
    /**
     * The number of iterations required to reach within TOL of the root.
     */
    private int numIterations;

    /**
     * An integer that signifies errors that may occur in the root finding
     * process.
     *
     * Possible values are:
     *   =  0: Nothing went wrong.
     *   = -1: Difference went to zero during the algorithm.
     *   = -2: Reached MAXITER iterations.
     */
    private int err;
    
    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * Basic constructor.
     *
     * @param p  The polynomial used for Secant.
     */
    public Secant(Polynomial p) {
        f = new Polynomial (p.coeff);
        // You need to fill in this method. (Completed)
    }

    // ========================================================
    // Accessor methods.
    // ========================================================
    
    /**
     * Returns the current value of the err instance variable.
     */
    public int getError() {
        return err;
        // You need to fill in this method. (Completed)
    }

    /**
     * Returns the current value of the numIterations instance variable.
     */
    public int getNumIterations() { 
        return numIterations;
        // You need to fill in this method. (Completed)
    }
    
    /**
     * Returns the current value of the root instance variable.
     */
    public Complex getRoot() {
        return root;
        // You need to fill in this method. (Completed)
    }

    /**
     * Returns the polynomial associated with this object.
     */
    public Polynomial getF() {
        return f;
        // You need to fill in this method. (Completed)
    }

    // ========================================================
    // Secant method (check the comment)
    // ========================================================
    
    /**
     * Given two complex numbers z0 and z1, apply Secant to the polynomial f in
     * order to find a root within tolerance TOL.
     *
     * One of three things may occur:
     *
     *   - The root is found, in which case, set root to the end result of the
     *     algorithm, numIterations to the number of iterations required to
     *     reach it and err to 0.
     *   - At some point the absolute difference between f(zn) and f(zn-1) becomes zero. 
     *     In this case, set err to -1 and return.
     *   - After MAXITER iterations the algorithm has not converged. In this 
     *     case set err to -2 and return.
     *        
     *
     * @param z0,z1  The initial starting points for the algorithm.
     */

    // iterate Secant method
    public void iterate(Complex z0, Complex z1) {
        numIterations = 0;
        Complex pz = new Complex(); //Sets a complex value to store previous value of z
        Complex ppz = new Complex(); // Sets a complex value to store the other previous value of z
        Complex num;
        Complex denom;
        root = new Complex();

        // Iterates until it reacher MAXITER or until an error occurs
        for(int i = 0; i<= MAXITER; i++){
            ppz.setReal(z0.getReal());
            ppz.setImag(z0.getImag());
            pz.setReal(z1.getReal());
            pz.setImag(z1.getImag());
            num = z1.add(z0.minus());
            denom = f.evaluate(z1).add(f.evaluate(z0).minus());

            // If denom (f(zn).f(z_(n-1)=0, returns error -1))
            if( denom.abs()<TOL){
                err = -1;
                return;
            }

            // Sets z1 to zn+1 = zn -f(zn)*(zn-z_(n-1))/(f(z)-f(z_(n-1))) and z_(n-1) to z_n
            z0 = z1;
            z1 = z1.add(f.evaluate(z1).multiply(num.divide(denom)).minus());
            numIterations = numIterations + 1;

            // Checks if |zm-z_(m-1)|<TOL
            if ((z1.add(pz.minus())).abs()<TOL && f.evaluate(z1).abs()<TOL){
                // Sets root to zm, err to 0 and numiterations 
                root.setReal(z1.getReal());
                root.setImag(z1.getImag());
                err = 0;
                numIterations = numIterations -1;
                return;
            }
        }
        // If MAXITER ends and no root is found or denom /=0, sets err to -2
        err = -2;
        return;
        
        // You need to fill in this method. (Completed)
    }
      
    // ========================================================
    // Tester function.
    // ========================================================
    
    public static void main(String[] args) {

        Complex[] coeff = new Complex[] { new Complex(-1.0,0.0), new Complex(), new Complex(), new Complex(1.0,0.0) };
        Polynomial p    = new Polynomial(coeff);
        Secant     n    = new Secant(p);
        
        n.iterate(new Complex (), new Complex(1.0));
        System.out.println("Iterations: " + n.getNumIterations());
        System.out.println("Error: " + n.getError());
        System.out.println("Root: " + n.getRoot());    
    }
}
