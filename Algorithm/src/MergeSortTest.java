/**
 * 2013-2-18
 * MergeSortTest.java
 * kenvi
 * mrwhite@163.com/kenvifly@gmail.com
 */

import edu.princeton.cs.algs4.StdRandom;
import junit.framework.TestCase;
import org.junit.Test;


/**
 * @author kenvi
 * 
 */
public class MergeSortTest extends TestCase {
	@Test
	public void testMerge() {
		Integer[] a = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		for (int i = 0; i < 1000; i++) {
			StdRandom.shuffle(a);
			MergeSort.sort(a);
		}
	}

	private boolean isSorted(Comparable[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			if (MergeSort.less(a[i + 1], a[i])) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void testDumpData() {
		Integer[] a = new Integer[] { 83, 66, 90, 91, 97, 38, 59, 45, 30, 54,
				82, 51 };
		MergeSort.sort(a);

	}

	@Test
	public void testDumpData2() {
		Integer[] a = new Integer[] { 69, 36, 89, 24, 61, 27, 35, 53, 10, 49,
				45, 32 };
		MergeSort.sort(a);
	}

	@Test
	public void testButtomUpMerge() {
		Integer[] a = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		for (int i = 0; i < 1000; i++) {
			StdRandom.shuffle(a);
			MergeSort.buttomUpSort(a);
			assertEquals(true, isSorted(a));
		}
	}

	@Test
	public void testButtomUpDump() {
		Integer[] a = new Integer[] { 48, 47, 29, 96, 54, 92, 43, 71, 35, 15 };
		MergeSort.buttomUpSort(a);
	}

	@Test
	public void testButtomUpDump2() {
		Integer[] a = new Integer[] { 26, 48, 39, 34, 85, 79, 50, 21, 54, 66 };

		MergeSort.buttomUpSort(a);
	}
}
