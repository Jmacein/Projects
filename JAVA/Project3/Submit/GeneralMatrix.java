/*
 * PROJECT III: GeneralMatrix.java
 *
 * This file contains a template for the class GeneralMatrix. Not all methods
 * are implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Matrix class.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

import java.util.Arrays;

public class GeneralMatrix extends Matrix {
    /**
     * This instance variable stores the elements of the matrix.
     */
    private double[][] data;

    /**
     * Constructor function: should initialise m and n through the Matrix
     * constructor and set up the data array.
     *
     * @param m  The first dimension of the array.
     * @param n  The second dimension of the array.
     */
    public GeneralMatrix(int m, int n) throws MatrixException {

        super(m,n);
        // If dimensions are correct, sets up the m and n data arrays.
        if(m>0 && n>0){
            data = new double[m][n];
        }
        // If dimensions are incorrect, throw error.
        else{
            throw new MatrixException("Not possible dimensions for the matrix");
        }
        // You need to fill in this method. (Completed)
    }

    /**
     * Constructor function. This is a copy constructor; it should create a
     * copy of the matrix A.
     *
     * @param A  The matrix to create a copy of.
     */
    public GeneralMatrix(GeneralMatrix A) {
        super(A.m,A.n);
        data = new double[A.m][A.n];
        // Iterates through all elements of A and copies them into a new matrix
        for (int i = 0; i<A.m;i++){
            for (int j = 0; j<A.n;j++){
                data[i][j] = A.getIJ(i,j);
            }
        }
    }
        // You need to fill in this method. (Completed)
    
    /**
     * Getter function: return the (i,j)'th entry of the matrix.
     *
     * @param i  The location in the first co-ordinate.
     * @param j  The location in the second co-ordinate.
     * @return   The (i,j)'th entry of the matrix.
     */
    public double getIJ(int i, int j) {

        // Checks if (i,j)th entry is in the matrix and returns it
        if(i>=0 && i<this.m && j>=0 &&j<this.n){
            return data[i][j];
        }

        // If not, throws error
        else{
            throw new MatrixException("The element is not in the matrix");
        }
        // You need to fill in this method.(Completed)
    }
    
    /**
     * Setter function: set the (i,j)'th entry of the data array.
     *
     * @param i    The location in the first co-ordinate.
     * @param j    The location in the second co-ordinate.
     * @param val  The value to set the (i,j)'th entry to.
     */
    public void setIJ(int i, int j, double val) {

        // Checks if (i,j)th entry is in the matrix and sets it to a given value
        if(i>=0 && i<this.m && j>=0 && j<this.n){
            data[i][j] = val;
        }

        // If not, throws error
        else{
            throw new MatrixException("Element is not in the matrix");
        }
        // You need to fill in this method.(Completed)
    }
    
    /**
     * Return the determinant of this matrix.
     *
     * @return The determinant of the matrix.
     */
    public double determinant() {
        if(this.n==this.m){
            double det = 1;
            double[] d = new double[1];

            // Runs decomp on matrix 
            GeneralMatrix LU = this.decomp(d);

            // det(A)=det(LU)=det(L)*det(U) = det(U)
            // det(L) = 1 because is in upper triangular form and its diagonal is all 1
            // U is in lower triangular form so det(U) is the product of the element in its diagonal
            for (int i=0;i<this.n;i++){
                det = det*LU.getIJ(i,i);
            }

            // Multiplies det by 1 or -1 accordingly
            det = det*d[0];

            return det;
        }
        else{
            throw new MatrixException("It is not a square matrix");
        }
        
        // You need to fill in this method.(Completed)
    }

    /**
     * Add the matrix to another matrix A.
     *
     * @param A  The Matrix to add to this matrix.
     * @return   The sum of this matrix with the matrix A.
     */
    public Matrix add(Matrix A) {
        // Checks if both matrices have same dimension and adds them 
        if(this.m==A.m && this.n==A.n){
            GeneralMatrix SumMatrix = new GeneralMatrix(A.m, A.n);
            for (int i = 0; i< this.m; i++){
                for (int j = 0; j<this.n; j++){
                    SumMatrix.setIJ(i,j,A.getIJ(i,j)+this.getIJ(i,j));
                }
            }
            return SumMatrix;
        }

        // If not, throws exception
        else{
            throw new MatrixException("Matrices cannot be added, they do not have the same dimension");
        }

        // You need to fill in this method.(Completed)
    }
    
    /**
     * Multiply the matrix by another matrix A. This is a _left_ product,
     * i.e. if this matrix is called B then it calculates the product BA.
     *
     * @param A  The Matrix to multiply by.
     * @return   The product of this matrix with the matrix A.
     */
    public Matrix multiply(Matrix A) {

        // Checks if the columns of the matrix is equal to the rows of A and multiplies them
        if(this.n==A.m){
        double total;
        GeneralMatrix MultiplicationMatrix = new GeneralMatrix(this.m, A.n);
        for (int i =0; i<this.m;i++){
            for (int j = 0; j<A.n;j++){
                total = 0;
                for (int k = 0; k< this.n; k++){
                    total = total + this.getIJ(i,k)*A.getIJ(k,j);
                }
                MultiplicationMatrix.setIJ(i,j,total);
            }
        }
        return MultiplicationMatrix;
        }

        // If not, throws error
        else{
            throw new MatrixException("Matrices cannot be multiplied");
        }
        
        // You need to fill in this method. (Completed)
    }

    /**
     * Multiply the matrix by a scalar.
     *
     * @param a  The scalar to multiply the matrix by.
     * @return   The product of this matrix with the scalar a.
     */
    public Matrix multiply(double a) {

        GeneralMatrix ScalMultMatrix = new GeneralMatrix(this.m,this.n);
        for (int i = 0; i<this.m;i++){
            for (int j = 0; j<this.n;j++){
                ScalMultMatrix.setIJ(i,j,a*getIJ(i,j));
            }
        }
        return ScalMultMatrix;
        // You need to fill in this method.(Completed)
    }

    /**
     * Populates the matrix with random numbers which are uniformly
     * distributed between 0 and 1.
     */
    public void random() {
        for(int i = 0;i<this.m;i++){
            for (int j =0;j<this.n;j++){
                this.setIJ(i,j,Math.random());
            }
        }
        // You need to fill in this method.(Completed)
    }

    /**
     * Returns the LU decomposition of this matrix; i.e. two matrices L and U
     * so that A = LU, where L is lower-diagonal and U is upper-diagonal.
     * 
     * On exit, decomp returns the two matrices in a single matrix by packing
     * both matrices as follows:
     *
     * [ u_11 u_12 u_13 u_14 ]
     * [ l_21 u_22 u_23 u_24 ]
     * [ l_31 l_32 u_33 u_34 ]
     * [ l_41 l_42 l_43 l_44 ]
     *
     * where u_ij are the elements of U and l_ij are the elements of l. When
     * calculating the determinant you will need to multiply by the value of
     * d[0] calculated by the function.
     * 
     * If the matrix is singular, then the routine throws a MatrixException.
     *
     * This method is an adaptation of the one found in the book "Numerical
     * Recipies in C" (see online for more details).
     * 
     * @param d  An array of length 1. On exit, the value contained in here
     *           will either be 1 or -1, which you can use to calculate the
     *           correct sign on the determinant.
     * @return   The LU decomposition of the matrix.
     */
    public GeneralMatrix decomp(double[] d) {
        // This method is complete. You should not even attempt to change it!!
        if (n != m)
            throw new MatrixException("Matrix is not square");
        if (d.length != 1)
            throw new MatrixException("d should be of length 1");
        
        int           i, imax = -10, j, k; 
        double        big, dum, sum, temp;
        double[]      vv   = new double[n];
        GeneralMatrix a    = new GeneralMatrix(this);
        
        d[0] = 1.0;
        
        for (i = 1; i <= n; i++) {
            big = 0.0;
            for (j = 1; j <= n; j++)
                if ((temp = Math.abs(a.data[i-1][j-1])) > big)
                    big = temp;
            if (big == 0.0)
                throw new MatrixException("Matrix is singular");
            vv[i-1] = 1.0/big;
        }
        
        for (j = 1; j <= n; j++) {
            for (i = 1; i < j; i++) {
                sum = a.data[i-1][j-1];
                for (k = 1; k < i; k++)
                    sum -= a.data[i-1][k-1]*a.data[k-1][j-1];
                a.data[i-1][j-1] = sum;
            }
            big = 0.0;
            for (i = j; i <= n; i++) {
                sum = a.data[i-1][j-1];
                for (k = 1; k < j; k++)
                    sum -= a.data[i-1][k-1]*a.data[k-1][j-1];
                a.data[i-1][j-1] = sum;
                if ((dum = vv[i-1]*Math.abs(sum)) >= big) {
                    big  = dum;
                    imax = i;
                }
            }
            if (j != imax) {
                for (k = 1; k <= n; k++) {
                    dum = a.data[imax-1][k-1];
                    a.data[imax-1][k-1] = a.data[j-1][k-1];
                    a.data[j-1][k-1] = dum;
                }
                d[0] = -d[0];
                vv[imax-1] = vv[j-1];
            }
            if (a.data[j-1][j-1] == 0.0)
                a.data[j-1][j-1] = 1.0e-20;
            if (j != n) {
                dum = 1.0/a.data[j-1][j-1];
                for (i = j+1; i <= n; i++)
                    a.data[i-1][j-1] *= dum;
            }
        }
        
        return a;
    }

    /*
     * Your tester function should go here.
     */
    public static void main(String[] args) {
        Matrix A = new GeneralMatrix(2,2);
        A.setIJ(0,0,1);
        A.setIJ(0,1,2);
        A.setIJ(1,0,-3);
        A.setIJ(1,1,1);

        Matrix B = new GeneralMatrix(2,2);
        B.setIJ(0,0,-2);
        B.setIJ(0,1,3);
        B.setIJ(1,0,1);
        B.setIJ(1,1,2);

        Matrix C = new GeneralMatrix(2,1);
        C.setIJ(0,0,3);
        C.setIJ(1,0,2);


        System.out.println("A = " + "\n"+ A);
        System.out.println("B = " + "\n"+ B);
        System.out.println("C = " + "\n"+ C);

        // Test getIJ 
        System.out.println("Element (1,0) of A is: " + A.getIJ(1,0));


        // Test determinant 

        System.out.println("Determinant of A is: " + A.determinant());
        System.out.println("Determinant of B is: " + B.determinant());

        // Test matrix operations 

        System.out.println("A+B= " + "\n" + A.add(B));

        System.out.println("BC = " + "\n" +B.multiply(C));

        System.out.println("2A = " + "\n" + A.multiply(2));
        
        // Test random

        Matrix D = new GeneralMatrix(3,4);
        D.random();
        System.out.println("D = " + "\n" + D);
    
        // You need to fill in this method.
    }
}