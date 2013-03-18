
import java.util.ArrayList;
import java.util.List;

/**
 * 2013-3-16
 * PointSET.java
 * kenvi
 * mrwhite@163.com/kenvifly@gmail.com
 */

/**
 * @author kenvi
 * 
 */
public class PointSET {
	private SET<Point2D> pointSet;

	public PointSET() {
		pointSet = new SET<Point2D>();
	}

	public boolean isEmpty() {
		return pointSet.isEmpty();
	}

	public int size() {
		return pointSet.size();
	}

	public boolean contains(Point2D p) {
		return pointSet.contains(p);
	}

	/**
	 * 
	 */
	public void draw() {
		for (Point2D p : pointSet)
			p.draw();

	}

	/**
	 * @param p
	 */
	public void insert(Point2D p) {
		pointSet.add(p);
	}

	/**
	 * @param query
	 * @return
	 */
	public Point2D nearest(Point2D query) {
		if (pointSet.isEmpty())
			return null;

		Point2D result = null;
		double distance = Double.MAX_VALUE;

		for (Point2D p : pointSet) {
			if (Double.compare(distance, p.distanceSquaredTo(query)) > 0) {
				distance = p.distanceSquaredTo(query);
				result = p;
			}
		}
		return result;
	}

	/**
	 * @param rect
	 * @return
	 */
	public Iterable<Point2D> range(RectHV rect) {
		List<Point2D> result = new ArrayList<Point2D>();
		for (Point2D point : pointSet) {
			if (rect.contains(point)) {
				result.add(point);
			}
		}
		return result;

	}

}
