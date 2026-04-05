/*
 * PROJECT III: TriMatrix.java
 *
 * This file contains a template for the class TriMatrix. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Matrix class.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class TriMatrix extends Matrix {
    /**
     * An array holding the diagonal elements of the matrix.
     */
    private double[] diag;

    /**
     * An array holding the upper-diagonal elements of the matrix.
     */
    private double[] upper;

    /**
     * An array holding the lower-diagonal elements of the matrix.
     */
    private double[] lower;
    
    /**
     * Constructor function: should initialise m and n through the Matrix
     * constructor and set up the data array.
     *
     * @param N  The dimension of the array.
     */
    public TriMatrix(int N) {
        super(N,N);
        // Checks if dimension is possible
        if(N>0){
            diag = new double[N];
            upper = new double[N-1];
            lower = new double[N-1];
        }
        // If not, throws error
        else{
            throw new MatrixException("The matrix cannot have this dimension");
        }
        // You need to fill in this method.(Completed)
    }
    
    /**
     * Getter function: return the (i,j)'th entry of the matrix.
     *
     * @param i  The location in the first co-ordinate.
     * @param j  The location in the second co-ordinate.
     * @return   The (i,j)'th entry of the matrix.
     */
    public double getIJ(int i, int j) {
        // Checks if (i,j)th entry is in the matrix and returns it
        if(i>=0 && i<this.n && j>=0 && j<this.n){
            if (i==j){
                return diag[j];
            }
            else if (i == j-1){
                return upper[i];
            }
            else if (i==j+1){
                return lower[j];
            }
            else{
                return 0;
            }
        }
        // If not, throws error
        else{
            throw new MatrixException("Element is not in matrix");
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
        if(i>=0 && i<this.n && j>=0 && j<this.n){
            if (i==j){
                diag[j] = val;
            }
            else if (i == j-1){
                upper[i] = val;
            }
            else if (i==j+1){
                lower[j] = val;
            }

            // If element is not in the diagonal, upper diagonal or lower diagonal, cannot be set so throws error
            else{
                throw new MatrixException("Cannot set this element");
            }
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
        double det = 1;

        // Runs decomp() on the matrix
        TriMatrix LU = this.decomp();

        // det(A)=det(LU)=det(L)*det(U) = det(U)
        // det(L) = 1 because is in upper triangular form and its diagonal are all 1
        // U is in lower triangular form so det(U) is the product of the element in its diagonal
        for (int i=0;i<this.n;i++){
            det = det*LU.getIJ(i,i);
        }
        return det;

        // You need to fill in this method.(Completed)
    }
    
    /**
     * Returns the LU decomposition of this matrix. See the formulation for a
     * more detailed description.
     * 
     * @return The LU decomposition of this matrix.
     */
    public TriMatrix decomp() {
        // Creates 3 arrays: upper diagonal, diagonal and lower diagonal
        double[] ud = new double[this.n-1];
        double[] md = new double[this.n];
        double[] ld = new double[this.n-1];
        
        /**
        * Let ai be the ith element in the diagonal of A
        * Let bi be the ith element in the lower diagonal of A
        * Let ci be the ith element in the upper diagonal of A
        * Let li the the ith element in the lower diagonal of L (diagonal of L are all 1)
        * Let mi be the ith element in the diagonal of U
        * Let ui be the ith element in the upper diagonal of U
        * Then m1 = a1
        * Then ui = ci
        * Then li = bi/ui
        * Then mi = ai - m(i-1)*c(i-1)
        */

        md[0] = this.getIJ(0,0);

        // Computes the elements of the upper diagonal of U
        for (int i=0; i<this.n-1; i++){
           ud[i] = this.getIJ(i,i+1);
        }

        // Computes the elements of the lower diagonal
        for (int i=0; i<this.n-1; i++){
            ld[i] = this.getIJ(i+1,i)/md[i];
            // Computes the elements of the diagonal
            for ( int j=1; j<this.n; j++){
                md[j] = this.getIJ(j,j)-ld[j-1]*ud[j-1];
            }
        }

        // Combines the elements of LU into a single matrix
        TriMatrix LU = new TriMatrix(this.n);
        for (int i = 0; i<this.n-1;i++){
            LU.setIJ(i,i+1,ud[i]);
            LU.setIJ(i+1,i,ld[i]);
        }
        for(int i = 0; i<this.n;i++){
            LU.setIJ(i,i,md[i]);
        }
        
        return LU;
        // You need to fill in this method.(Completed)
    }

    /**
     * Add the matrix to another matrix A.
     *
     * @param A  The Matrix to add to this matrix.
     * @return   The sum of this matrix with the matrix A.
     */
    public Matrix add(Matrix A){

        // Checks if both matrices have same dimension and adds them
        if(this.m==A.m && this.n == A.n){
            TriMatrix TriSumMatrix = new TriMatrix(this.n);
            for ( int i = 0; i<this.n-1;i++){
                TriSumMatrix.setIJ(i,i+1,this.getIJ(i,i+1)+A.getIJ(i,i+1));
            }
            for ( int i = 0; i<this.n;i++){
                TriSumMatrix.setIJ(i,i,this.getIJ(i,i)+A.getIJ(i,i));
            }
            for ( int i = 0; i<this.n-1;i++){
                TriSumMatrix.setIJ(i+1,i,this.getIJ(i+1,i)+A.getIJ(i+1,i));
            }
            return TriSumMatrix;
        }
        // If not, throws error
        else{
            throw new MatrixException("Cannot add this matrices");
        }
        


        // You need to fill in this method.
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
            GeneralMatrix ProdTriMatrix = new GeneralMatrix(this.m,A.n);
            double total = 0;
            for (int i =0; i<this.m;i++){
                for (int j = 0; j<A.n;j++){
                    total = 0;
                    for (int k = 0; k< this.n; k++){
                        total = total + this.getIJ(i,k)*A.getIJ(k,j);
                    }
                    ProdTriMatrix.setIJ(i,j,total);
                }
            }
            return ProdTriMatrix;
        }

        // If not, throws error
        else{
            throw new MatrixException("Cannot multiply this matrices");
        }
        // You need to fill in this method.(Completed)
    }
    
    /**
     * Multiply the matrix by a scalar.
     *
     * @param a  The scalar to multiply the matrix by.
     * @return   The product of this matrix with the scalar a.
     */
    public Matrix multiply(double a) {
        TriMatrix ScalMatrix = new TriMatrix(this.n);
        for(int i=0;i<this.n-1;i++){
                ScalMatrix.setIJ(i,i+1,this.getIJ(i,i+1)*a);
                ScalMatrix.setIJ(i+1,i,this.getIJ(i+1,i)*a);
                
        }
        for(int i=0;i<this.n;i++){
            ScalMatrix.setIJ(i,i,this.getIJ(i,i)*a); 

        }
        return ScalMatrix;
        
        // You need to fill in this method.(Completed)
    }

    /**
     * Populates the matrix with random numbers which are uniformly
     * distributed between 0 and 1.
     */
    public void random() {
        for(int i = 0; i<this.n-1;i++){
            this.setIJ(i,i+1,Math.random());
            this.setIJ(i+1,i,Math.random());
        }
        for(int i=0;i<this.n;i++){
            this.setIJ(i,i,Math.random());
        }
        // You need to fill in this method.(Completed)
    }
    
    /*
     * Your tester function should go here.
     */
    public static void main(String[] args) {
        Matrix A = new TriMatrix(3);
        A.setIJ(0,0,1);
        A.setIJ(1,1,-3);
        A.setIJ(2,2,2);
        A.setIJ(0,1,-1);
        A.setIJ(1,2,1);
        A.setIJ(1,0,-2);
        A.setIJ(2,1,1);

        Matrix B = new TriMatrix(4);
        B.setIJ(0,0,1);
        B.setIJ(1,1,-2);
        B.setIJ(2,2,1);
        B.setIJ(3,3,4);
        B.setIJ(0,1,-3);
        B.setIJ(1,2,2);
        B.setIJ(2,3,1);
        B.setIJ(1,0,1);
        B.setIJ(2,1,-1);
        B.setIJ(3,2,3);

        Matrix C = new TriMatrix(3);
        C.setIJ(0,0,1);
        C.setIJ(1,1,2);
        C.setIJ(2,2,1);
        C.setIJ(0,1,-2);
        C.setIJ(1,2,3);
        C.setIJ(1,0,-3);
        C.setIJ(2,1,2);


        System.out.println("A = " + "\n" + A);
        System.out.println("B = " + "\n" + B);
        System.out.println("C = " + "\n" + C);
        // Test getIJ
        System.out.println("The (0,1) element of A is: " + A.getIJ(0,1));
        System.out.println("The (2,1) element of B is:" + B.getIJ(2,1));

        // Test determinants
        System.out.println("Determinant of A is: " + A.determinant());
        System.out.println("Determinant of B is: " + B.determinant());

        // Test matrix operations 

        System.out.println("A+C = " + "\n" + A.add(C));

        System.out.println("CA = " + "\n" + C.multiply(A));

        System.out.println("2A = " + "\n" + A.multiply(2));


        // Test random

        Matrix D = new TriMatrix(5);
        D.random();
        System.out.println("D = " + "\n" + D);
     
        // You need to fill in this method.
    }
}