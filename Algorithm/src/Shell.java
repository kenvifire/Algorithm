public class Shell {
	public static void sort(int[] a) {
		int N = a.length;
		int h = 4;
	//	while (h < N / 3)
	//		h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, ...
	//	while (h >= 1) { // h-sort the array.
			for (int i = h; i < N; i++) {
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
					exch(a, j, j - h);
			}
		//	h = h / 3;
	//	}
	}

	private static boolean less(int v, int w) {
		return v < w;
	}

	private static boolean exch(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		return true;
	}
	
	public static void main(String[] args){
		int[] a = { 52 ,48 ,63, 76, 35, 37, 73, 53, 93, 32 };
		Shell.sort(a);
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
		
	}
}