import java.io.*;

public class Knapsack{

    static double startTime = System.nanoTime();

    public static void main(final String[] args) {


       /* Integer[] weights = {10, 40, 20, 30};
        Integer[] values = {60, 40, 100, 120};
        int n = 4;
        int totalWeight = 50; */

        try {
            final BufferedReader br = new BufferedReader(new FileReader(args[0]));
    
        String[] s = br.readLine().split(" "); // Get n
            final int n = Integer.parseInt(s[0]);
    
        s = br.readLine().split(" "); // Get profits
        final int[] profits = new int[n];
        for(int i=0; i<n; i++){
          profits[i] = Integer.parseInt(s[0]);
        }
    
        s = br.readLine().split(" "); // Get weights
        final int[] weights = new int[n];
        for(int i=0; i<n; i++){
          weights[i] = Integer.parseInt(s[0]);
        }
    
        s = br.readLine().split(" "); // Get maxWeight
            final int w = Integer.parseInt(s[0]);
    
            Greedy(w, profits, weights, n);
            final double endTime = System.nanoTime();
            System.out.println("Took "+(endTime - startTime)/1000000000 + " sec");
    
            br.close();
          }catch(final FileNotFoundException e){
              System.out.println("No se localizó el archivo " + e);
          }catch(final IOException e){
              System.out.println("Ocurrió un error de I/O" + e);
          }

       
    }

    public static void Greedy(final int maxWeight, final Integer[] values, final Integer[] weights, final Integer n) {
        final Float[] densities = new Float[n];
        for(int i = 0; i < n; i++) {
            densities[i] = (float)values[i]/weights[i];
        }

        Sort.quicksort(densities, values, weights);

        final for(int i = n-1; i >= 0 && weights[i] <= sumWeight; i--) {
            solution[i] = true;
            sumWeight = sumWeight - weights[i];
        }

        int finalBenefit = 0;
        for(int i = 0; i < solution.length; i++) {
            if(solution[i]) {
                finalBenefit += values[i];
                System.out.println("Object with value: "+values[i]+" , weight: "+weights[i]+" and density: "+densities[i]);
            }
        }

        System.out.println("Final benefit: "+finalBenefit);
    }

}