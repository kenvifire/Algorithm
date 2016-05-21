import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

/**
 * 2013-2-19
 * Point.java
 * kenvi
 * mrwhite@163.com/kenvifly@gmail.com
 */

/**
 * @author kenvi
 * 
 */
public class Point implements Comparable<Point> {
	public final Comparator<Point> SLOPE_ORDER = new SlopComparator();

	private final int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw() {
		StdDraw.point(x, y);
	}

	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);

	}

	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}

	public int compareTo(Point that) {
		if (that == null)
			throw new NullPointerException();
		if (this.y < that.y) {
			return -1;
		} else if (this.y > that.y) {
			return 1;
		} else {
			if (this.x < that.x)
				return -1;
			else if (this.x > that.x)
				return 1;
			else
				return 0;

		}

	}

	public double slopeTo(Point that) {
		double deltax = that.x - this.x;
		double deltay = that.y - this.y;
		if (deltay == 0 && deltax != 0)
			return 0.0;
		if (deltax == 0 && deltay != 0)
			return Double.POSITIVE_INFINITY;
		if (deltax == 0 && deltay == 0)
			return Double.NEGATIVE_INFINITY;
		else
			return deltay / deltax;

	}

	// public double slopeTo(Point that) {
	// double deltax = Double.compare(this.x, that.x);
	// double deltay = Double.compare(this.y, that.y);
	// if (deltay == 0 && deltax != 0)
	// return 0.0;
	// if (deltax == 0 && deltay != 0)
	// return Double.POSITIVE_INFINITY;
	// if (deltax == 0 && deltay == 0)
	// return Double.NEGATIVE_INFINITY;
	// else
	// return deltay / deltax;
	//
	// }

	private class SlopComparator implements Comparator<Point> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Point o1, Point o2) {
			double k1 = slopeTo(o1);
			double k2 = slopeTo(o2);
			// positive infinity
			if (k1 == Double.POSITIVE_INFINITY) {
				if (k2 == Double.POSITIVE_INFINITY)
					return 0;
				else
					return 1;
			}
			// negtive infinity
			if (k1 == Double.NEGATIVE_INFINITY) {
				if (k2 == Double.NEGATIVE_INFINITY)
					return 0;
				else
					return -1;
			}
			return Double.compare(k1, k2);
		}
	}
}
