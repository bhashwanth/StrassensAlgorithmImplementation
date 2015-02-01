package testing;

import java.util.Scanner;

public class StrassensMatrixMultiplication {
	
	int[][] splitMatrix(int[][] matrix, int row, int column){
		int n = matrix.length;
		int[][] result = new int[n/2][n/2];
		for(int i=0, a=row; i<n/2; i++,a++){
			for(int j=0, b=column; j<n/2; j++,b++){
				result[i][j] = matrix[a][b];
			}
		}
		return result;
	}
	
	void joinMatrices(int[][] matrix, int[][] resultantMatrix, int row, int column){
		for(int i=0, a=row; i<matrix.length; i++,a++){
			for(int j=0, b=column; j<matrix.length; j++,b++){
				resultantMatrix[a][b] = matrix[i][j];
			}
		}
	}
	
	int[][] addMatrices(int[][] matrix1, int[][] matrix2){
		int n = matrix1.length;
		int[][] result = new int[n][n];
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				result[i][j] = matrix1[i][j]+matrix2[i][j];
			}
		}
		return result;
	}
	
	int[][] subtractMatrices(int[][] matrix1, int[][] matrix2){
		int n = matrix1.length;
		int[][] result = new int[n][n];
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				result[i][j] = matrix1[i][j]-matrix2[i][j];
			}
		}
		return result;
	}
	
	int[][] strassensMultiplication(int[][] matrix1, int[][] matrix2){
		int n = matrix1.length;
		int[][] resultantMatrix = new int[n][n];
		
		if(n == 1){
			resultantMatrix[0][0] = matrix1[0][0]*matrix2[0][0];
		}else{
			int[][] A11 = splitMatrix(matrix1, 0, 0);
			int[][] A12 = splitMatrix(matrix1, 0, n/2);
			int[][] A21 = splitMatrix(matrix1, n/2, 0);
			int[][] A22 = splitMatrix(matrix1, n/2, n/2);
			int[][] B11 = splitMatrix(matrix2, 0, 0);
			int[][] B12 = splitMatrix(matrix2, 0, n/2);
			int[][] B21 = splitMatrix(matrix2, n/2, 0);
			int[][] B22 = splitMatrix(matrix2, n/2, n/2);
			
			int [][] M1 = strassensMultiplication(addMatrices(A11, A22), addMatrices(B11, B22));
            int [][] M2 = strassensMultiplication(addMatrices(A21, A22), B11);
            int [][] M3 = strassensMultiplication(A11, subtractMatrices(B12, B22));
            int [][] M4 = strassensMultiplication(A22, subtractMatrices(B21, B11));
            int [][] M5 = strassensMultiplication(addMatrices(A11, A12), B22);
            int [][] M6 = strassensMultiplication(subtractMatrices(A21, A11), addMatrices(B11, B12));
            int [][] M7 = strassensMultiplication(subtractMatrices(A12, A22), addMatrices(B21, B22));
 
            int [][] C11 = addMatrices(subtractMatrices(addMatrices(M1, M4), M5), M7);
            int [][] C12 = addMatrices(M3, M5);
            int [][] C21 = addMatrices(M2, M4);
            int [][] C22 = addMatrices(subtractMatrices(addMatrices(M1, M3), M2), M6);
 
            joinMatrices(C11, resultantMatrix, 0 , 0);
            joinMatrices(C12, resultantMatrix, 0 , n/2);
            joinMatrices(C21, resultantMatrix, n/2, 0);
            joinMatrices(C22, resultantMatrix, n/2, n/2);
		}
		
		return resultantMatrix; 
	}

	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		
		System.out.println("Enter the order of the matrices:");
		int n = stdin.nextInt();
		
		System.out.println("Enter the first matrix of size "+n+":");
		int[][] matrix1 = new int[n][n];
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				matrix1[i][j] = stdin.nextInt();  
			}
		}
		
		System.out.println("Enter the second matrix of size "+n+":");
		int[][] matrix2 = new int[n][n];
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				matrix2[i][j] = stdin.nextInt();  
			}
		}

		StrassensMatrixMultiplication smm = new StrassensMatrixMultiplication();
		int[][] resultantMatrix = smm.strassensMultiplication(matrix1, matrix2);
		
		System.out.println("Resultant Matrix:");
		for(int i=0; i<resultantMatrix.length; i++){
			for(int j=0; j<resultantMatrix.length; j++){
				System.out.print(resultantMatrix[i][j]+" ");  
			}
			System.out.println();
		}
		
		stdin.close();
	}

}
