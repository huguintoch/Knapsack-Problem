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
            int[] profits = new int[n];
            for (int i = 0; i < n; i++) {
                profits[i] = Integer.parseInt(s[i]);
            }

            s = br.readLine().split(" "); // Get weights
            int[] weights = new int[n];
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

    public static void Greedy(int maxWeight, int[] profits, int[] weights, int n) {
        Float[] densities = new Float[n];
        Integer[] positions = new Integer[n];
        for(int i = 0; i < n; i++) {
            densities[i] = (float)profits[i]/weights[i];
            positions[i] = i;
        }

        Sort.mergesort(densities, positions);
        
        boolean[] solution = new boolean[n];
        int sumWeight = maxWeight;
        int maxProfit = 0;
        for(int i = n-1; i >= 0 && weights[positions[i]] <= sumWeight; i--) {
            solution[i] = true;
            maxProfit += profits[positions[i]];
            sumWeight = sumWeight - weights[positions[i]];
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("resultados.txt"));
            writer.write(maxProfit + "\n");
            for(int i=0;i<solution.length;i++) {
                if(solution[i]) {
                    writer.write(positions[i].toString()+" ");
                } 
            }
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error de I/O" + e);
        }
    }
}