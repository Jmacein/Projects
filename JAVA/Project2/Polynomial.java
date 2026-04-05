import java.util.Scanner;

/*
 * PROJECT II: Polynomial.java
 *
 * This file contains a template for the class Polynomial. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * This class is designed to use Complex in order to represent polynomials
 * with complex co-efficients. It provides very basic functionality and there
 * are very few methods to implement! The project formulation contains a
 * complete description.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file! You should also test this class using the main()
 * function.
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class Polynomial {
    /**
     * An array storing the complex co-efficients of the polynomial.
     */
    Complex[] coeff;

    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * General constructor: assigns this polynomial a given set of
     * co-efficients.
     *
     * @param coeff  The co-efficients to use for this polynomial.
     */
    // Starts with degree = "length array - 1". Iterates over last elements to see if they are 0.0+0.0i until the first non-zero element. 
    public Polynomial(Complex[] coeff) {
        int degree = coeff.length-1;
        for (int i = coeff.length -1 ; i >= -1; i-- ){
            if (coeff[i].getReal() == 0 && coeff[i].getImag() == 0){
                degree += -1;
    
            } 
    // If element not 0.0+0.0i, the loop breaks.
            else {
                break;
            }
        }
    // Creates new array where x^n has non-zero coefficient. Copies elements of array to new coeff array without 0.0 last elements.
        this.coeff = new Complex[degree+1];

        for (int j = 0; j <= degree ; j++){
            this.coeff[j] = coeff[j];
        }
        // You need to fill in this function. (Completed)
    }
    
    
    /**
     * Default constructor: sets the Polynomial to the zero polynomial.
     */
    public Polynomial() {
        Complex a = new Complex();
        this.coeff = new Complex[] {a};

        // You need to fill in this function. (Completed)
    }

    // ========================================================
    // Operations and functions with polynomials.
    // ========================================================

    /**
     * Return the coefficients array.
     *
     * @return  The coefficients array.
     */
    public Complex[] getCoeff() {
        return coeff;
        // You need to fill in this function. (Completed)
    }

    /**
     * Create a string representation of the polynomial.
     *
     * For example: (1.0+1.0i)+(1.0+2.0i)X+(1.0+3.0i)X^2
     */
    public String toString() {
        String str = "(" + coeff[0].toString() + ")+" +
                     "(" + coeff[1].toString() + ")X";
                   
        for(int i = 2; i<=coeff.length-1; i ++){
            str = str + "+(" + coeff[i].toString() + ")X^" + i;

        }
        return str;
         
        // You need to fill in this function. (Completed)
    }

    /**
     * Returns the degree of this polynomial.
     */
    public int degree() {
        return coeff.length -1;
        // You need to fill in this function. (Completed)
    }

    /**
     * Evaluates the polynomial at a given point z.
     *
     * @param z  The point at which to evaluate the polynomial
     * @return   The complex number P(z).
     */
    public Complex evaluate(Complex z) {
        // Uses P(z) = 𝑎0 + 𝑎1𝑧 + 𝑎2𝑧^2 + a3z^3 = a0 + z(a1+z(a2+za3))
        
        Complex a = new Complex(coeff[coeff.length -1].getReal(),coeff[coeff.length - 1].getImag());
        Complex b;

        for (int i=this.coeff.length-2; i>=0 ;i--){
            // Starts with last coefficient an times z and adds the previous coefficient 
            b = a.multiply(z);
            b = b.add(coeff[i]);
            a.setReal(b.getReal());
            a.setImag(b.getImag());
        }
        return a;

        // You need to fill in this function. (Completed)
    }

    
    // ========================================================
    // Tester function.
    // ========================================================

    public static void main(String[] args) {

        Complex[] coeff = new Complex[] {new Complex(1.0,-3.0), new Complex(0.0,-1.0), new Complex(), new Complex(1,1), new Complex(-1,1), new Complex(0,0)};
        Polynomial p = new Polynomial(coeff);
		
        Complex z = p.evaluate(new Complex(-1.0,3.0));
        System.out.println("P(x)= " + p.toString());
        System.out.println("Degree: " + p.degree());
        System.out.println("P(-1+3i)= " + z.toString());
        // You need to fill in this function with your own testing code.
  

    }
}