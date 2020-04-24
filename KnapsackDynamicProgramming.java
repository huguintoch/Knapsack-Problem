// Reference code: https://www.educative.io/courses/grokking-dynamic-programming-patterns-for-coding-interviews/RM1BDv71V60#top-down-dynamic-programming-with-memoization

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

class KnapsackDynamicProgramming {

	static long startTime = System.nanoTime();
	
	private static int[][] dpMain;

	public static int solveKnapsack(int[] profits, int[] weights, int capacity) {
		// Edge cases
		if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
			return 0;

		int n = profits.length;
		int[][] dp = new int[n][capacity + 1];

		// Fill profits with 0 when weight is 0
		for(int i=0; i < n; i++)
			dp[i][0] = 0;
		// If we have only one weight, we will take it if it is not more than the capacity
		for(int c=0; c <= capacity; c++) {
			if(weights[0] <= c)
				dp[0][c] = profits[0];
		}

		// Process all sub-arrays for all the capacities
		for(int i=1; i < n; i++) {
			for(int c=1; c <= capacity; c++) {
				int profit1= 0, profit2 = 0;
				// Include the item, if it is not more than the capacity
				if(weights[i] <= c)
					profit1 = profits[i] + dp[i-1][c-weights[i]];
				// Exclude the item
				profit2 = dp[i-1][c];
				// Take maximum
				dp[i][c] = Math.max(profit1, profit2);
			}
		}

		dpMain = dp;
		return dp[n-1][capacity];
	}

	private static int[] getSelectedElements(int dp[][], int[] weights, int[] profits, int capacity){
		int[] used = new int[weights.length];
		if (!(capacity <= 0 || profits.length == 0 || weights.length != profits.length)){
			int totalProfit = dp[weights.length-1][capacity];
			int index = 0;
			for(int i=weights.length-1; i > 0; i--) {
				if(totalProfit != dp[i-1][capacity]) {
					used[index] = weights[i];
					capacity -= weights[i];
					totalProfit -= profits[i];
					index++;
				}
			}
		}
		return used;
	}

	public static void main(String[] args) {

		int maxProfit = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader(args[0]));

			String[] s = br.readLine().split(" "); // Get n
			int n = Integer.parseInt(s[0]);
			//System.out.println("# of elements: " + n);

			s = br.readLine().split(" "); // Get profits
			int[] profits = new int[n];
			for(int i=0; i<n; i++){
				profits[i] = Integer.parseInt(s[i]);
			}
			//System.out.println("Profits: " + Arrays.toString(profits));

			s = br.readLine().split(" "); // Get weights
			int[] weights = new int[n];
			for(int i=0; i<n; i++){
				weights[i] = Integer.parseInt(s[i]);
			}
			//System.out.println("Weights: " + Arrays.toString(weights));

			s = br.readLine().split(" "); // Get maxWeight
			int w = Integer.parseInt(s[0]);
			//System.out.println("Maximum weight: " + w);

			maxProfit = KnapsackDynamicProgramming.solveKnapsack(profits, weights, w);
			//System.out.println("Total knapsack profit: " + maxProfit);      

      		br.close();
      
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("resultados.txt"));
				writer.write("MaxProfit: " + maxProfit + "\n");
				int[] used = getSelectedElements(dpMain, weights, profits, w);
				writer.write("Weights used: ");
				for(int i=0;i<used.length;i++) {
					if(used[i]!=0)
						writer.write(used[i] + " ");
				}
				writer.write("\n");
				writer.write("Matrix: \n");
				for(int i=0;i<dpMain.length;i++) {
					for(int j=0;j<dpMain[0].length;j++) {
						writer.write(dpMain[i][j] + " ");
					}
					writer.write("\n");
				}
				writer.close();
			} catch (IOException e) {
				System.out.println("Ocurrió un error de I/O" + e);
			}

		}catch(FileNotFoundException e){
			System.out.println("No se localizó el archivo " + e);
		}catch(IOException e){
			System.out.println("Ocurrió un error de I/O" + e);
		}
	}
}