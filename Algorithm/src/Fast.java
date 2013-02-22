import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set<Line> set = new HashSet<Line>();

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
		fast(set, points);
		for (Line line : set) {
			// System.out.println("draw");
			line.start.drawTo(line.end);
		}
		StdDraw.show(0);

	}

	private static void fast(Set<Line> set, Point[] points) {
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
				if (j > points.length - 2)
					break;
				int count = 2;
				int start = j;
				while (comparator.compare(points[j], points[j + 1]) == 0) {

					count++;
					j++;
					if (j > points.length - 2)
						break;
				}
				if (count >= 4) {
					dumpPoints(points, 0, start, j, set);
				}
				j++;
			}

		}

	}

	private static void dumpPoints(Point[] points, int origin, int i, int j,
			Set<Line> set) {

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

		for (int k = 0; k < aup.length - 1; k++) {
			StdOut.printf(aup[k] + " -> ");
		}
		StdOut.printf(aup[aup.length - 1] + "\n");

	}

	private static class Line {
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
			// System.out.println("compare" + this + "->" + that);
			return false;
		}

		@Override
		public int hashCode() {
			int hashCode = start.hashCode() * 3 + end.hashCode() * 13;
			return hashCode;

		}

	}
}
