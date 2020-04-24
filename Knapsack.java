import java.io.*;

public class Knapsack {

    static double startTime;
    static double endTime;

    public static void main(String[] args) {
        int maxProfit = 0;
        boolean[] solution;
        Integer[] positions;
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            //BufferedReader br = new BufferedReader(new FileReader("Prueba.txt"));

            String[] s = br.readLine().split(" "); // Get n
            int n = 0;
            try {
                n = Integer.parseInt(s[0]);
            } catch (NumberFormatException e) {
                System.out.println("La cantidad de elementos en la mochila debe ser un número.");
                br.close();
                return;
            }
            
            s = br.readLine().split(" "); // Get profits
            int[] profits = new int[s.length];
            try {
                for (int i = 0; i < s.length; i++) {
                    profits[i] = Integer.parseInt(s[i]);
                }
            } catch (NumberFormatException e) {
                System.out.println("El beneficio de todos los objetos debe ser representado con un número.");
				br.close();
				return;
            }
            
            s = br.readLine().split(" "); // Get weights
            int[] weights = new int[s.length];
            try {
                for (int i = 0; i < s.length; i++) {
                    weights[i] = Integer.parseInt(s[i]);
                    if(weights[i]==0) {
						System.out.println("El peso del objeto en el indice " + i + " no puede ser 0.");
						br.close();
						return;
					}
                }
            } catch(NumberFormatException e) {
                System.out.println("El peso de todos los objetos debe ser mayor a 0.");
				br.close();
				return;
            }

            if(weights.length != profits.length) {
				System.out.println("Se debe ingresar el mismo número de beneficios y pesos.");
				br.close();
				return;
			}
			
			if(weights.length != n) {
				System.out.println("El número de elementos no concuerda con los objetos ingresados.");
				br.close();
				return;
			}
            
            s = br.readLine().split(" "); // Get maxWeight
            int w = 0;
            try {
                w = Integer.parseInt(s[0]);
                if(w==0) {
					System.out.println("El peso máximo de la mochila debe ser mayor a 0.");
					br.close();
					return;
				}
            } catch (Exception e) {
                System.out.println("El peso máximo de la mochila debe ser representado con un número.");
				br.close();
				return;
            }
			
			if(profits.length==0 || weights.length==0) {
				System.out.println("Se debe ingresar por lo menos un peso y un beneficio.");
				br.close();
				return;
            }
            
            solution = new boolean[n];
            positions = new Integer[n];
            startTime = System.nanoTime();

            try {
                maxProfit = Greedy(n, w, profits, weights, solution, positions);
            }catch(NumberFormatException e) {
                System.out.println("Los números generados no caben dentro de un entero.");
                br.close();
                return;
            }

            endTime = System.nanoTime();
            System.out.println("Took " + (endTime - startTime) / 1000000000 + " sec");
            br.close();

            try {
                int file = 0;
                file = Integer.parseInt(args[0].charAt(3)+"");
                if(file==0) 
                    file = 10;
                
                FileWriter fileWriter;
                if(file!=10) {
                    fileWriter = new FileWriter("res_ga0"+file+".txt");
                }else {
                    fileWriter = new FileWriter("res_ga"+file+".txt");
                }
                BufferedWriter writer = new BufferedWriter(fileWriter);
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
        } catch (FileNotFoundException e) {
            System.out.println("No se localizó el archivo: " + e);
        } catch (IOException e) {
            System.out.println("Ocurrió un error de I/O: " + e);
        }

        
    }

    public static int Greedy(int n, int maxWeight, int[] profits, int[] weights, boolean[] solution, Integer[] positions) {
        Float[] densities = new Float[n];
        for(int i = 0; i < n; i++) {
            densities[i] = (float)profits[i]/weights[i];
            positions[i] = i;
        }

        Sort.mergesort(densities, positions);
        
        int sumWeight = maxWeight;
        int maxProfit = 0;

        for(int i = n-1; i >= 0 && weights[positions[i]] <= sumWeight; i--) {
            solution[i] = true;
            maxProfit += profits[positions[i]];
            sumWeight = sumWeight - weights[positions[i]];
        }
        
        return maxProfit;
    }
}