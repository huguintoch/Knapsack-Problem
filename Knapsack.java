import java.io.*;

public class Knapsack {

    static double startTime = System.nanoTime();

    public static void main(String[] args) {
        try {
            //BufferedReader br = new BufferedReader(new FileReader(args[0]));
            BufferedReader br = new BufferedReader(new FileReader("Prueba.txt"));

            String[] s = br.readLine().split(" "); // Get n
            int n = Integer.parseInt(s[0]);

            s = br.readLine().split(" "); // Get profits
            Integer[] profits = new Integer[n];
            for (int i = 0; i < n; i++) {
                profits[i] = Integer.parseInt(s[i]);
            }

            s = br.readLine().split(" "); // Get weights
            Integer[] weights = new Integer[n];
            for (int i = 0; i < n; i++) {
                weights[i] = Integer.parseInt(s[i]);
            }

            s = br.readLine().split(" "); // Get maxWeight
            int w = Integer.parseInt(s[0]);

            Greedy(w, profits, weights, n);
            double endTime = System.nanoTime();
            System.out.println("Took " + (endTime - startTime) / 1000000000 + " sec");

            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se localizó el archivo " + e);
        } catch (IOException e) {
            System.out.println("Ocurrió un error de I/O" + e);
        }
    }

    public static void Greedy(int maxWeight, Integer[] profits, Integer[] weights, int n) {
        Float[] densities = new Float[n];
        for(int i = 0; i < n; i++) {
            densities[i] = (float)profits[i]/weights[i];
        }

        Sort.quicksort(densities, profits, weights);
        
        boolean[] solution = new boolean[n];
        int sumWeight = maxWeight;

        for(int i = n-1; i >= 0 && weights[i] <= sumWeight; i--) {
            solution[i] = true;
            sumWeight = sumWeight - weights[i];
        }

        int maxProfit = 0;
        int[] used = new int[n];
        for(int i = 0; i < solution.length; i++) {
            if(solution[i]) {
                maxProfit += profits[i];
                used[i] = weights[i];
            }
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("resultados.txt"));
            writer.write("MaxProfit: " + maxProfit + "\n");
            writer.write("Weights used: ");
            for(int i=0;i<used.length;i++) {
                if(used[i] != 0)
                    writer.write(used[i] + " ");
            }
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error de I/O" + e);
        }
    }

}