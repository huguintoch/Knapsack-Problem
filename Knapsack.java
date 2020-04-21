public class Knapsack {

    static double startTime = System.nanoTime();

    public static void main(String[] args) {
        Integer[] weights = {2,3,5,6,4};
        Integer[] values = {6,1,12,8,7};
        int n = 5;
        int totalWeight = 11;
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
        
        for(int i=0; i<densities.length/2; i++){
            Integer tempValue = values[i];
            values[i] = values[values.length-i-1];
            values[values.length-i-1] = tempValue;
            Integer tempWeight = weights[i];
            weights[i] = weights[weights.length-i-1];
            weights[weights.length-i-1] = tempWeight;
            Float tempDensity = densities[i];
            densities[i] = densities[densities.length-i-1];
            densities[densities.length-i-1] = tempDensity;
        }

        boolean[] solution = new boolean[n];

        int sumWeight = maxWeight;
        for(int i = 0; i < n && weights[i] <= sumWeight; i++) {
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