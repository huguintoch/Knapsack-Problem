// Reference code: https://www.educative.io/courses/grokking-dynamic-programming-patterns-for-coding-interviews/RM1BDv71V60#top-down-dynamic-programming-with-memoization

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class KnapsackDynamicProgramming {

	static long startTime = System.nanoTime();
	
	private static int[][] dpMain;

	public static int solveKnapsack(int[] profits, int[] weights, int capacity) {
		// Edge cases

		int n = profits.length;
		int[][] dp = new int[n][capacity + 1];

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
					used[index] = i;
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
			int n=0;
			try {
				n = Integer.parseInt(s[0]);
			}catch (NumberFormatException e) {
				System.out.println("La cantidad de elementos en la mochila debe ser un número.");
				br.close();
				return;
			}
			
			//System.out.println("# of elements: " + n);

			s = br.readLine().split(" "); // Get profits
			int[] profits = new int[n];
			try {
				for(int i=0; i<n; i++){
					profits[i] = Integer.parseInt(s[i]);
				}
			}catch(NumberFormatException e) {
				System.out.println("El beneficio de todos los objetos debe ser representado con un número.");
				br.close();
				return;
			}catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Se deben ingresar el mismo número de pesos y beneficios.");
				br.close();
				return;
			}
			
			//System.out.println("Profits: " + Arrays.toString(profits));

			s = br.readLine().split(" "); // Get weights
			int[] weights = new int[n];
			try {
				for(int i=0; i<n; i++){
					weights[i] = Integer.parseInt(s[i]);
					if(weights[i]==0) {
						System.out.println("El peso del objeto en el indice " + i + " no puede ser 0.");
						br.close();
						return;
					}
				}
			}catch(NumberFormatException e) {
				System.out.println("El peso de todos los objetos debe ser mayor a 0.");
				br.close();
				return;
			}catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Se deben ingresar el mismo número de pesos y beneficios.");
				br.close();
				return;
			}
			
			//System.out.println("Weights: " + Arrays.toString(weights));

			s = br.readLine().split(" "); // Get maxWeight
			int w = 0;
			try {
				w = Integer.parseInt(s[0]);
				if(w==0) {
					System.out.println("El peso máximo de la mochila debe ser mayor a 0.");
					br.close();
					return;
				}
			}catch (NumberFormatException e){
				System.out.println("El peso máximo de la mochila debe ser representado con un número.");
				br.close();
				return;
			}
			
			
			if(profits.length==0 || weights.length==0) {
				System.out.println("Se debe ingresar por lo menos un peso y un beneficio.");
				br.close();
				return;
			}
			
			
			try {
				maxProfit = KnapsackDynamicProgramming.solveKnapsack(profits, weights, w);
			}catch(NumberFormatException e) {
				System.out.println("Los números generados no caben dentro de un entero.");
				br.close();
				return;
			}
			
			//System.out.println("Total knapsack profit: " + maxProfit);      

      		br.close();
      
			try {
				int file = 0;
				file = Integer.parseInt(args[0].charAt(3)+"");

				if(file==0) 
					file = 10;
				
				FileWriter fileWriter;
				if(file!=10) {
					fileWriter = new FileWriter("res_dp0"+file+".txt");
				}else {
					fileWriter = new FileWriter("res_dp"+file+".txt");
				}
					
				
				BufferedWriter writer = new BufferedWriter(fileWriter);
				writer.write(maxProfit + "\n");
				int[] used = getSelectedElements(dpMain, weights, profits, w);
				for(int i=0;i<used.length;i++) {
					if(used[i]!=0)
						writer.write(used[i] + " ");
				}
				writer.write("\n");
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