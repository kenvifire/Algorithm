import java.util.Arrays;
import java.util.Comparator;

/**
 * 2013-2-18
 * Point2D.java
 * kenvi
 * mrwhite@163.com/kenvifly@gmail.com
 */

/**
 * @author kenvi
 * 
 */
public class Point2D {
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Comparator<Point2D> POLOR_ORDER = new PolarOrder();
	private final double x;
	private final double y;
	private final String name;

	public Point2D(String name, double x, double y) {
		this.x = x;
		this.y = y;
		this.name = name;
	}

	public static int ccw(Point2D a, Point2D b, Point2D c) {
		// System.out.println("ccw:(" + a.getName() + "," + b.getName() + ","
		// + c.getName() + ")");
		double area = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
		if (area < 0)
			return -1;
		else if (area > 0)
			return 1;
		else
			return 0;

	}

	private class PolarOrder implements Comparator<Point2D> {
		public int compare(Point2D q1, Point2D q2) {
			double dy1 = q1.y - y;
			double dy2 = q2.y - y;
			if (dy1 == 0 && dy2 == 0) {
				return 0;
			} else if (dy1 >= 0 && dy2 < 0)
				return -1;
			else if (dy2 >= 0 && dy1 < 0)
				return +1;
			else
				return -ccw(Point2D.this, q1, q2);
		}
	}

	public void sort(Point2D[] points) {
		Arrays.sort(points, POLOR_ORDER);
	}

	public String getName() {
		return this.name;
	}

}
