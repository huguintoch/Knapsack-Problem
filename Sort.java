
public class Sort {
	//Quick Sort
	public static <E extends Comparable<E>> void quicksort(E[] datos, Integer[] datos1) {
		quicksort(datos, datos1, 0, datos.length);
	}
	
	private static <E extends Comparable<E>> void quicksort(E[] datos, Integer[] datos1, int left,int right) {
		if(left < right) {
			int p = particionar(datos, datos1, left, right);
			quicksort(datos, datos1, left, p);
			quicksort(datos, datos1, p+1, right);
		}
	}
	
	private static <E extends Comparable<E>> int particionar(E[] datos, Integer[] datos1, int l, int r) {
		E p = datos[l];
		int i = l + 1;
		for(int j = l + 1; j < r; j++) {
			if(datos[j].compareTo(p) <= 0) {
                swap(datos, i, j);
                swap(datos1, i, j);
				i++;
			}
		}
        swap(datos, l, i-1);
        swap(datos1, l, i-1);
		return i-1;
	}
	
	private static <E> void swap(E[] valores, int i, int j ) {
		E temp = valores[i];
		valores[i] = valores[j];
		valores[j] = temp;
	}

	//Merge Sort
	public static <E extends Comparable<E>> void mergesort(E[] datos, Integer[]datos1) {
		mergesort(datos, datos1, 0, datos.length-1);
	}
	
	private static <E extends Comparable<E>> void mergesort(E[] datos, Integer[]datos1, int ini, int fin) {
		int mid;
		if(ini < fin) {
			mid = (ini+fin)/2;
			mergesort(datos, datos1, ini, mid);
			mergesort(datos, datos1, mid+1, fin);
			merge(datos, datos1, ini, fin);
		}
	}
	
	private static <E extends Comparable<E>> void merge(E[] datos, Integer[]datos1, int ini, int fin) {
		int mid = (ini+fin)/2;
		E[] tempLeft = (E[]) new Comparable[mid-ini+1] ;
		E[] tempRight = (E[]) new Comparable[fin-mid] ;
		Integer[] tempLeft1 = new Integer[mid-ini+1] ;
		Integer[] tempRight1 = new Integer[fin-mid] ;
		
		for(int i = 0; i < mid-ini+1; i++) {
			tempLeft[i] = datos[ini+i];
			tempLeft1[i] = datos1[ini+i];
		}
		for(int i = 0; i < fin-mid; i++) {
			tempRight[i] = datos[mid+1+i];
			tempRight1[i] = datos1[mid+1+i];
		}
		
		int cont = ini,
			i = 0,
			j = 0;
		
		while(i < mid-ini+1 && j < fin-mid ) {
			if(tempLeft[i].compareTo(tempRight[j]) <= 0) {
				datos[cont] = tempLeft[i];
				datos1[cont] = tempLeft1[i];
				i++;
			} else {
				datos[cont] = tempRight[j];
				datos1[cont] = tempRight1[j];
				j++;
			}
			cont++;
		}
		
		while(i < mid-ini+1) {
			datos[cont] = tempLeft[i];
			datos1[cont] = tempLeft1[i];
			i++;
			cont++;
		}
		
		while(j < fin-mid) {
			datos[cont] = tempRight[j];
			datos1[cont] = tempRight1[j];
			j++;
			cont++;
		}
	}
}