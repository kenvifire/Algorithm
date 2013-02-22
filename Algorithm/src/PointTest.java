import junit.framework.TestCase;

import org.junit.Test;

/**
 * 2013-2-20
 * PointTest.java
 * kenvi
 * mrwhite@163.com/kenvifly@gmail.com
 */

/**
 * @author kenvi
 * 
 */
public class PointTest extends TestCase {

	@Test
	public void testComparator() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(1, 1);
		assertEquals(-1, p1.SLOPE_ORDER.compare(p1, p2));

	}

	@Test
	public void testComparator2() {
		Point p1 = new Point(10000, 0);
		Point p2 = new Point(6000, 7000);
		Point p3 = new Point(7000, 3000);
		assertEquals(-1, p1.SLOPE_ORDER.compare(p2, p3));

	}

	@Test
	public void testSlopTo() {
		Point p1 = new Point(327, 315);
		Point p2 = new Point(386, 315);
		assertEquals(0.0, p1.slopeTo(p2));

	}

	@Test
	public void testCompareTo() {
		Point p1 = new Point(199, 14);
		Point p2 = new Point(371, 14);
		assertEquals(p1.compareTo(p2), -p2.compareTo(p1));
	}

	@Test
	public void testCompareTo2() {
		Point p = new Point(424, 364);
		Point q = new Point(357, 364);
		assertEquals(1, p.compareTo(q));
	}

	// @Test
	// public void testLineCompare() {
	// SET<Line> set = new SET<Line>();
	// Point start = new Point(1000, 17000);
	// Point end = new Point(29000, 17000);
	// Line line = new Line(start, end);
	// set.add(line);
	//
	// Point start1 = new Point(1000, 17000);
	// Point end1 = new Point(29000, 17000);
	// Line line1 = new Line(start1, end1);
	// assertEquals(true, set.contains(line1));
	// }

	private static class Line implements Comparable<Line> {
		private Point start, end;

		public Line(Point start, Point end) {

			this.start = start;
			this.end = end;
		}

		public String toString() {
			return start + "->" + end;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			Line that = (Line) obj;
			if (this.start.compareTo(that.start) == 0
					&& this.end.compareTo(that.end) == 0) {
				return true;
			}
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(Line o) {
			if (this.equals(o))
				return 0;
			return 1;
		}

	}

}
