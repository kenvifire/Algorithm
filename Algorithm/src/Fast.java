import java.util.Arrays;
import java.util.Comparator;

/**
 * 2013-2-19
 * Fast.java
 * kenvi
 * mrwhite@163.com/kenvifly@gmail.com
 */

/**
 * @author kenvi
 * 
 */
public class Fast {
	private static SET<Line> set = new SET<Line>();

	/**
	 * @param args
	 */
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

			points[i] = p;
		}
		fast(points);
		StdDraw.show(0);
	}

	private static void fast(Point[] points) {
		if (points.length < 4)
			return;

		Comparator<Point>[] comparators = new Comparator[points.length];
		for (int i = 0; i < points.length; i++) {
			comparators[i] = points[i].SLOPE_ORDER;
		}

		for (int i = 0; i < points.length; i++) {
			Arrays.sort(points, comparators[i]);
			Comparator<Point> comparator = comparators[i];
			int j = 1;

			while (true) {
				// System.out.println(j);
				if (j > points.length - 2)
					break;
				int count = 2;
				int start = j;
				while (comparator.compare(points[j], points[j + 1]) == 0) {

					count++;
					j++;
					if (j > points.length - 3)
						break;
				}
				if (count >= 4) {
					dumpPoints(points, 0, start, j);
				}
				j++;
			}
			// removeFristPoint(points);
		}

	}

	// private static void exchange(Point[] points, int i, int j) {
	//
	// Point tmp = points[i];
	// points[i] = points[j];
	// points[j] = tmp;
	//
	// }

	private static void dumpPoints(Point[] points, int origin, int i, int j) {

		Point[] aup = new Point[j - i + 2];
		aup[0] = points[origin];
		for (int k = 1; k < aup.length; k++) {
			aup[k] = points[i + k - 1];
		}

		Arrays.sort(aup);
		Line line = new Line(aup[0], aup[aup.length - 1]);
		if (set.contains(line)) {
			return;
		}
		set.add(line);
		System.out.println(line);

		// StdOut.printf(points[origin] + "->");
		for (int k = 0; k < aup.length - 1; k++) {
			StdOut.printf(aup[k] + " -> ");
		}
		StdOut.printf(aup[aup.length - 1] + "\n");
		aup[0].drawTo(aup[aup.length - 1]);
		for (int k = 1; k < aup.length - 2; k++) {
			aup[k].draw();

		}

	}

	// public static void removeFristPoint(Point[] points) {
	// for (int i = 0; i < points.length - 1; i++) {
	// points[i] = points[i + 1];
	// }
	// }

	//
	// public static void dumpPointDatas(Point[] points) {
	// for (int i = 1; i < points.length; i++) {
	//
	// }
	//
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
