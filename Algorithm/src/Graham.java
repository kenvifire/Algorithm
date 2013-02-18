import java.util.ArrayList;
import java.util.List;

/**
 * 2013-2-18
 * Graham.java
 * kenvi
 * mrwhite@163.com/kenvifly@gmail.com
 */

/**
 * @author kenvi
 * 
 */
public class Graham {
	private Point2D[] points;

	public Graham(Point2D[] points) {
		this.points = points;
	}

	public void dumpPoints() {
		System.out.println("-------------dumping----------------");
		for (Point2D poiont : points) {
			System.out.println(poiont.getName() + "(" + poiont.getX() + ","
					+ poiont.getY() + ")");
		}
	}

	public void findSolution() {
		// find the point with the smallest y cord
		int origin = 0;
		for (int i = 0; i < points.length; i++) {
			if (MergeSort.less(points[i].getY(), points[origin].getY())) {
				origin = i;
			}
		}
		//
		// exchange origin and first point
		Point2D temp = points[0];
		points[0] = points[origin];
		points[origin] = temp;

		// sort
		points[0].sort(points);
		// dumping points
		// dumpPoints();

		List<Point2D> solution = new ArrayList<Point2D>();
		for (int i = 0; i < points.length; i++) {
			solution.add(points[i]);
			// System.out.println("adding point :" + points[i].getName());
			if (solution.size() < 3)
				continue;

			List<Point2D> discard = new ArrayList<Point2D>();
			for (int j = 1; j < solution.size() - 1; j++) {
				if (Point2D.ccw(solution.get(j - 1), solution.get(j),
						solution.get(solution.size() - 1)) <= 0) {
					discard.add(solution.get(j));
					System.out.println();
				}
			}
			for (int k = discard.size() - 1; k >= 0; k--) {

				solution.remove(discard.get(k));
				System.out.println("discard:" + discard.get(k).getName());
			}

		}
	}
}
