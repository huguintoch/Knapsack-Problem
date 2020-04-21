
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
}