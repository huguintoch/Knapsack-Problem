public class Knapsack {

    static double startTime = System.nanoTime();

    public static void main(String[] args) {
        Integer[] weights = {10, 40, 20, 30};
        Integer[] values = {60, 40, 100, 120};
        int n = 4;
        int totalWeight = 50;
        Greedy(totalWeight, values, weights, n);
        double endTime = System.nanoTime();
        System.out.println("Took "+(endTime - startTime)/1000000000 + " sec");
    }

    public static void Greedy(int maxWeight, Integer[] values, Integer[] weights, Integer n) {
        Float[] densities = new Float[n];
        for(int i = 0; i < n; i++) {
            densities[i] = (float)values[i]/weights[i];
        }

        Sort.quicksort(densities, values, weights);

        boolean[] solution = new boolean[n];

        int sumWeight = maxWeight;
        for(int i = n-1; i >= 0 && weights[i] <= sumWeight; i--) {
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