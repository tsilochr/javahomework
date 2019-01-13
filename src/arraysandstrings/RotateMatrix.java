package arraysandstrings;

public class RotateMatrix {
	public static void main(String[] args) {
		short [][] matrix = createMatrix(3);
		print(matrix);
		System.out.println();
		rotate(matrix);
		print(matrix);
	}
	

	private static short[][] createMatrix(int N) {
		short matrix [][] = new short[N][N];
		short index = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				matrix[i][j] = ++index;
			}
		}
		return matrix;
	}


	private static void print(short[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}


	private static void rotate(short[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i; j < matrix.length-i-1; j++) {
				/*
				System.out.println(matrix[i][j]); //1
				System.out.println(matrix[i][matrix.length-j-1]); //2
				System.out.println(matrix[matrix.length-i-1][matrix.length-j-1]); //4
				System.out.println(matrix[matrix.length-j-1][j]); //3
				*/
				
				short tmp = matrix[i][j]; // top
				matrix[i][j] = matrix[matrix.length-j-1][j];
				matrix[matrix.length-j-1][j] = matrix[matrix.length-i-1][matrix.length-j-1];
				matrix[matrix.length-i-1][matrix.length-j-1] = matrix[i][matrix.length-j-1];
				matrix[i][matrix.length-j-1] = tmp;
				
			}
		}	
	}
}
