
public class Sort {
	public static <E extends Comparable<E>> void quicksort(E[] datos, Integer[] datos1, Integer[] datos2) {
		quicksort(datos, datos1, datos2, 0, datos.length);
	}
	
	private static <E extends Comparable<E>> void quicksort(E[] datos, Integer[] datos1, Integer[] datos2, int left,int right) {
		if(left < right) {
			int p = particionar(datos, datos1, datos2, left, right);
			quicksort(datos, datos1, datos2, left, p);
			quicksort(datos, datos1, datos2, p+1, right);
		}
	}
	
	private static <E extends Comparable<E>> int particionar(E[] datos, Integer[] datos1, Integer[] datos2, int l, int r) {
		E p = datos[l];
		int i = l + 1;
		for(int j = l + 1; j < r; j++) {
			if(datos[j].compareTo(p) <= 0) {
                swap(datos, i, j);
                swap(datos1, i, j);
                swap(datos2, i, j);
				i++;
			}
		}
        swap(datos, l, i-1);
        swap(datos1, l, i-1);
        swap(datos2, l, i-1);
		return i-1;
	}
	
	private static <E> void swap(E[] valores, int i, int j ) {
		E temp = valores[i];
		valores[i] = valores[j];
		valores[j] = temp;
	}
	
/*public class shellSort { 
	// An utility function to print array of size 
	static void printArray(float[] arr) { 
		int n = arr.length; 
		for (int i = 0; i < n; ++i) 
			System.out.print(arr[i] + " "); 
			System.out.println(); 
	} 
	  
	// function to sort arr using shellSort 
	private static float sort(float arr[]) { 
		int n = arr.length; 
	  
		// Start with a big gap, then reduce the gap 
		for (int gap = n / 2; gap > 0; gap /= 2) { 
			// Do a gapped insertion sort for this gap size. 
			// The first gap elements a[0..gap-1] are already 
			// in gapped order keep adding one more element 
			// until the entire array is gap sorted 
			for (int i = gap; i < n; i += 1) { 
				// add a[i] to the elements that have been gap 
				// sorted save a[i] in temp and make a hole at 
				// position i 
				float temp =   arr[i]; 
	  
				// shift earlier gap-sorted elements up until 
				// the correct location for a[i] is found 
				int j; 
				for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) 
					arr[j] = arr[j - gap]; 
	  
				// put temp (the original a[i]) in its correct 
				// location 
				arr[j] = temp; 
			} 
		} 
		return 0; 
	} 
	  
		// Driver method 
	public static void main(String args[]) { 
		float arr[] = { 12, 34, 54, 2, 3,1.0f, 1.54f, 254.4f, 128.4f, 1.001f, 1.02f}; 
		System.out.println("Array before sorting"); 
		printArray(arr); 
		System.out.println("");
	  
	
		sort(arr); 
	  
		System.out.println("Array after sorting"); 
		printArray(arr); 
	} 
} */
}