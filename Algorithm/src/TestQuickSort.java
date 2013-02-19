import junit.framework.TestCase;

import org.junit.Test;

/**
 * 2013-2-19
 * TestQuickSort.java
 * kenvi
 * mrwhite@163.com/kenvifly@gmail.com
 */

/**
 * @author kenvi
 * 
 */
public class TestQuickSort extends TestCase {
	@Test
	public void testPartition() {
		Integer[] a = new Integer[] { 158, 91, 85, 13, 16, 86, 49, 94, 69, 62,
				83, 45 };
		QuickSort.partition(a, 0, a.length - 1);
		for (int i : a) {
			System.out.print(i + " ");
		}
	}

	@Test
	public void testPartition2() {
		Character[] a = new Character[] { 'A', 'A', 'B', 'B', 'A', 'B', 'A',
				'A', 'A', 'A', 'B', 'A' };
		int result = QuickSort.partition(a, 0, a.length - 1);
		// System.out.println(result);

		for (char i : a) {
			System.out.print(i + " ");
		}
	}

	@Test
	public void testPartition3() {
		Integer[] a = new Integer[] { 51, 71, 83, 33, 93, 92, 56, 55, 19, 18,
				14, 31 };
		QuickSort.partition(a, 0, a.length - 1);
		for (int i : a) {
			System.out.print(i + " ");
		}
	}

	@Test
	public void testChage() {
		Character[] a = new Character[] { 'A', 'A', 'B', 'B', 'A', 'B', 'A',
				'A', 'A', 'A', 'B', 'A' };
		QuickSort.exchange(a, 1, 9);
		QuickSort.dumpData(a);

	}

	@Test
	public void testThreeWaySort() {
		Integer[] a = new Integer[] { 58, 46, 68, 86, 94, 58, 58, 33, 98, 58 };
		QuickSort.threeWaySort(a, 0, a.length - 1);
		QuickSort.dumpData(a);

	}
}
