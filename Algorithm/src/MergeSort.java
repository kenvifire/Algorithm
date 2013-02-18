/**
 * 2013-2-18
 * MergeSort.java
 * kenvi
 * mrwhite@163.com/kenvifly@gmail.com
 */

/**
 * @author kenvi
 * 
 */
public class MergeSort {

	public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}

	public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid,
			int hi) {
		for (int i = lo; i <= hi; i++) {
			aux[i] = a[i];
		}
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];

		}
		dumpData(a);

	}

	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);

	}

	public static boolean less(Comparable a, Comparable b) {
		if (a.compareTo(b) < 0)
			return true;
		return false;
	}

	private static void dumpData(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.printf(a[i].toString() + " ");
		}
		System.out.println();
	}

	public static void buttomUpSort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		for (int sz = 1; sz < a.length; sz += sz) {
			for (int lo = 0; lo < a.length - sz; lo += 2 * sz) {
				merge(a, aux, lo, lo + sz - 1,
						Math.min(lo + sz + sz - 1, a.length - 1));
			}
		}
	}

}
