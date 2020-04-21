// Reference: https://www.educative.io/courses/grokking-dynamic-programming-patterns-for-coding-interviews/RM1BDv71V60#top-down-dynamic-programming-with-memoization

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class KnapsackDynamicProgramming {

  static long startTime = System.nanoTime();

  public int solveKnapsack(int[] profits, int[] weights, int capacity) {
    // basic checks
    if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
      return 0;

    int n = profits.length;
    int[][] dp = new int[n][capacity + 1];

    // populate the capacity=0 columns, with '0' capacity we have '0' profit
    for(int i=0; i < n; i++)
      dp[i][0] = 0;

    // if we have only one weight, we will take it if it is not more than the capacity
    for(int c=0; c <= capacity; c++) {
      if(weights[0] <= c)
        dp[0][c] = profits[0];
    }

    // process all sub-arrays for all the capacities
    for(int i=1; i < n; i++) {
      for(int c=1; c <= capacity; c++) {
        int profit1= 0, profit2 = 0;
        // include the item, if it is not more than the capacity
        if(weights[i] <= c)
          profit1 = profits[i] + dp[i-1][c-weights[i]];
        // exclude the item
        profit2 = dp[i-1][c];
        // take maximum
        dp[i][c] = Math.max(profit1, profit2);
      }
    }

    this.printSelectedElements(dp, weights, profits, capacity);

    // maximum profit will be at the bottom-right corner.
    return dp[n-1][capacity];
  }

  private void printSelectedElements(int dp[][], int[] weights, int[] profits, int capacity){
    System.out.print("Selected weights:");
    int totalProfit = dp[weights.length-1][capacity];
    for(int i=weights.length-1; i > 0; i--) {
      if(totalProfit != dp[i-1][capacity]) {
        System.out.print(" " + weights[i]);
        capacity -= weights[i];
        totalProfit -= profits[i];
      }
    }
    if(totalProfit != 0)
      System.out.print(" " + weights[0]);
    System.out.println("");
  }

  public static void main(String[] args) {

    try {
		  BufferedReader br = new BufferedReader(new FileReader(args[0]));

      String[] s = br.readLine().split(" "); // Get n
  		int n = Integer.parseInt(s[0]);

      System.out.println(n);

      s = br.readLine().split(" "); // Get profits
      int[] profits = new int[n];
      for(int i=0; i<n; i++){
        profits[i] = Integer.parseInt(s[i]);
      }

      System.out.println(Arrays.toString(profits));

      s = br.readLine().split(" "); // Get weights
      int[] weights = new int[n];
      for(int i=0; i<n; i++){
        weights[i] = Integer.parseInt(s[i]);
      }

      System.out.println(Arrays.toString(weights));

      s = br.readLine().split(" "); // Get maxWeight
      int w = Integer.parseInt(s[0]);
      
      System.out.println(w);

      KnapsackDynamicProgramming ks = new KnapsackDynamicProgramming();
      int maxProfit = ks.solveKnapsack(profits, weights, w);

      System.out.println("Total knapsack profit:" + maxProfit);      

		  br.close();
		}catch(FileNotFoundException e){
			System.out.println("No se localizó el archivo " + e);
		}catch(IOException e){
			System.out.println("Ocurrió un error de I/O" + e);
		}

  }
}
