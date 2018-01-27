package com.misikirmehari.floyd;

/**
 * Implementation of Floyd's shortest path using a directed graph  
 * CS 4050 : Project 5  
 * 
 * @author Abdalla Elmedani
 * @author Misikir Mehari
 * 
 */
import java.util.Scanner;

public class Floyd {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		/**
		 * Get the number of Vertices from user
		 */
		System.out.println("******************************************");
		System.out.println("*                                        *");
		System.out.println("*  Floyd's Shortest Path Algorithm       *");
		System.out.println("*                                        *");
		System.out.println("******************************************");
		System.out.println("\n");
		System.out.print("Enter the number of vertices ? ");

		/**
		 * Variable to hold the number of vertices
		 */
		int NumberOfVertices = input.nextInt();

		/**
		 * Accept input from user and initialize directed graph
		 */
		int[][] initialDirectedGraph = getInputFromUser(NumberOfVertices);

		/**
		 * 2D array to hold the intermediate vertex score
		 */
		int[][] intermediateDirectedGraph = new int[NumberOfVertices][NumberOfVertices];

		/**
		 * Apply Floyd's algorithm and print
		 */
		for (int D = 0; D <= NumberOfVertices; D++) {

			System.out.println("\nD(" + D + ")");
			print(initialDirectedGraph, intermediateDirectedGraph);
			if (D < NumberOfVertices)
				applyFloydsAlgorithm(D, initialDirectedGraph, intermediateDirectedGraph);

		}

	}

	/**
	 * Apply Floyd's algorithm on a Directed graph
	 * 
	 * @param d
	 * 		
	 * @param initialDirectedGraph
	 * 			
	 * @param intermediateDirectedGraph
	 * 
	 * @return 
	 * 		returns updated directed Graph 
	 */
	public static int[][] applyFloydsAlgorithm(int d, int[][] initialDirectedGraph, int[][] intermediateDirectedGraph) {

		// Check Row
		for (int row = 0; row < intermediateDirectedGraph.length; row++) {

			// Escape the row where it is the same as the current d
			if (row == d)
				continue;

			// Check column
			for (int column = 0; column < intermediateDirectedGraph.length; column++) {

				// Escape the diagonals of the matrix
				if (row == column)
					continue;

				// Escape the column where it is the same as current d

				if (column == d)
					continue;

				// Escape if either the row or column has infinity
				if (initialDirectedGraph[row][d] == -1 || initialDirectedGraph[d][column] == -1)
					continue;

				else if (initialDirectedGraph[row][d] + initialDirectedGraph[d][column] != -1
						&& initialDirectedGraph[row][column] == -1) {

					initialDirectedGraph[row][column] = initialDirectedGraph[row][d] + initialDirectedGraph[d][column];
					intermediateDirectedGraph[row][column] = d + 1;
				}

				else if (initialDirectedGraph[row][d]
						+ initialDirectedGraph[d][column] < initialDirectedGraph[row][column]) {
					initialDirectedGraph[row][column] = initialDirectedGraph[row][d] + initialDirectedGraph[d][column];
					intermediateDirectedGraph[row][column] = d + 1;
				}
			}
		}

		return initialDirectedGraph;
	}

	/**
	 * Prints a directed graph along with updated weight scores
	 * @param initialDirectedGraph
	 * @param intermediateDirectedGraph
	 */
	public static void print(int[][] initialDirectedGraph, int[][] intermediateDirectedGraph) {

		// Used to print the header for the table
		int[] index = new int[initialDirectedGraph.length];

		for (int i = 1; i <= index.length; i++) {

			System.out.print("\t" + i);

		}
		
		System.out.println();

		for (int row = 0; row < initialDirectedGraph.length; row++) {
			System.out.println("\t-----------------------------------");
			System.out.print("  " + (row + 1) + " ");
			for (int column = 0; column < initialDirectedGraph.length; column++) {

				if (initialDirectedGraph[row][column] == -1) {

					System.out.print("\t-" + " " + generateSubscript(intermediateDirectedGraph[row][column]) + "   ");
				} else
					System.out.print("\t" + initialDirectedGraph[row][column] + " "
							+ generateSubscript(intermediateDirectedGraph[row][column]) + "   ");
			}
			System.out.println();

		}
		System.out.println("\t-----------------------------------");
	}

	/**
	 * 
	 * @param i
	 * 		an integer value passed as an argument to convert to a subscript 
	 * @return
	 * 		a string which is a subscript of the input value 
	 */		
	public static String generateSubscript(int i) {
		StringBuilder sb = new StringBuilder();
		for (char ch : String.valueOf(i).toCharArray()) {
			sb.append((char) ('\u2080' + (ch - '0')));
		}
		return sb.toString();
	}

	/**
	 * Accepts the weights of vertices from user
	 * @param numberOfVertices
	 * @return
	 */

	public static int[][] getInputFromUser(int numberOfVertices) {
		int[][] tempMatrix = new int[numberOfVertices][numberOfVertices];

		for (int row = 0; row < numberOfVertices; row++) {
			for (int col = 0; col < tempMatrix[row].length; col++) {

				if (row == col) {

					tempMatrix[row][col] = 0;

				} else if (row != col) {
					System.out.print("Enter the Weight from Vertice " + (row + 1) + " to Vertice " + (col + 1) + ": ");
					tempMatrix[row][col] = input.nextInt();

				}

			}

		}
		input.close();
		return tempMatrix;

	}
}
