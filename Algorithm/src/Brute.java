import java.util.Arrays;
import java.util.Comparator;

/**
 * 2013-2-19
 * Brute.java
 * kenvi
 * mrwhite@163.com/kenvifly@gmail.com
 */

/**
 * @author kenvi
 * 
 */
public class Brute {
	public static void main(String[] args) {

		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);
		In in = new In(args[0]);

		int N = in.readInt();
		Point[] points = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			Point p = new Point(x, y);
			p.draw();
			points[i] = p;
		}
		brute(points);
		StdDraw.show(0);
	}

	private static void brute(Point[] points) {
		Arrays.sort(points);
		for (int i = 0; i < points.length - 3; i++) {
			for (int j = i + 1; j < points.length - 2; j++) {
				for (int k = j + 1; k < points.length - 1; k++) {
					for (int n = k + 1; n < points.length; n++) {
						// System.out.println(i + "," + j + "," + k + "," + n);
						Comparator<Point> comparator = points[i].SLOPE_ORDER;
						if (comparator.compare(points[j], points[k]) == 0
								&& comparator.compare(points[k], points[n]) == 0) {
							StdOut.printf(points[i] + " -> " + points[j]
									+ " -> " + points[k] + " -> " + points[n]
									+ "\n");
							points[i].drawTo(points[n]);

						}
					}
				}
			}
		}

	}
}
