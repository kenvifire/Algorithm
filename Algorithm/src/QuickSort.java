/**
 * 2013-2-19
 * QuickSort.java
 * kenvi
 * mrwhite@163.com/kenvifly@gmail.com
 */

/**
 * @author kenvi
 * 
 */
public class QuickSort {

	public static int partition(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi + 1;

		while (true) {
			while (less(a[++i], a[lo]))
				if (i == hi)
					break;
			while (less(a[lo], a[--j]))
				if (j == lo)
					break;
			if (i >= j)
				break;
			exchange(a, i, j);
			dumpData(a);
		}
		exchange(a, j, lo);

		return j;
	}

	public static boolean less(Comparable a, Comparable b) {
		if (a.compareTo(b) < 0)
			return true;
		return false;
	}

	public static void exchange(Comparable[] a, int i, int j) {
		System.out.println("exchange " + i + "," + j);
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void dumpData(Comparable[] a) {
		System.out.println("-------------dumping data---------");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i].toString() + " ");
		}
		System.out.println("\n-----------------------------------");
	}

	public static void threeWaySort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int lt = lo, gt = hi;
		Comparable v = a[lo];
		int i = lo;
		while (i <= gt) {
			int comp = a[i].compareTo(v);
			if (comp < 0)
				exchange(a, lt++, i++);
			else if (comp > 0)
				exchange(a, gt--, i);
			else
				i++;

		}
	}
}
