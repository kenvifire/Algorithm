import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * 2013-3-16
 * KdTree.java
 * kenvi
 * mrwhite@163.com/kenvifly@gmail.com
 */

/**
 * @author kenvi
 * 
 */
public class KdTree {
	private int size;
	private KdNode root;

	public KdTree() {
		size = 0;
	}

	private static class KdNode {
		Point2D key;
		int level;
		KdNode left;
		KdNode right;
		RectHV rect;

		public KdNode(Point2D key, int level, RectHV rect) {
			this.key = key;
			this.level = level;
			this.rect = rect;
		}

	}

	public boolean contains(Point2D point) {
		if (root == null)
			return false;
		KdNode searchNode = root;

		while (searchNode != null) {

			if (searchNode.key.equals(point))
				return true;
			// odd
			if (searchNode.level % 2 == 1) {
				if (Double.compare(point.x(), searchNode.key.x()) <= 0) {
					searchNode = searchNode.left;
				} else {
					searchNode = searchNode.right;
				}

			} else {
				if (Double.compare(point.y(), searchNode.key.y()) <= 0) {
					searchNode = searchNode.left;
				} else {
					searchNode = searchNode.right;
				}

			}

		}
		return false;
	}

	/**
	 * 
	 */
	public void draw() {

		draw(root);
	}

	private void draw(KdNode root) {
		if (root == null)
			return;

		Color origin = StdDraw.getPenColor();
		// vertical:red
		if (root.level % 2 == 1) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(root.key.x(), root.rect.ymin(), root.key.x(),
					root.rect.ymax());
			// System.out.println("Line :vertical:(" + root.key.x() + ","
			// + root.rect.ymin() + "-->" + root.key.x() + ","
			// + root.rect.ymax() + ")");
		}
		// horizontal:blue
		else {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.line(root.rect.xmin(), root.key.y(), root.rect.xmax(),
					root.key.y());
			// System.out.println("Line :horizontal:(" + root.rect.xmin() + ","
			// + root.key.y() + "-->" + root.rect.ymin() + ","
			// + root.key.y() + ")");

		}
		StdDraw.setPenColor(origin);

		draw(root.left);
		draw(root.right);
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	/**
	 * @param p
	 */
	public void insert(Point2D p) {
		if (contains(p))
			return;
		size++;
		if (root == null) {
			RectHV rect = new RectHV(0, 0, 1, 1);
			root = new KdNode(p, 1, rect);
			return;
		}

		KdNode searchNode = root;
		while (searchNode != null) {

			// if level is odd,then x is the key,vertical split
			if (searchNode.level % 2 == 1) {
				if (p.x() <= searchNode.key.x()) {// left
					if (searchNode.left == null) {
						RectHV rect = new RectHV(searchNode.rect.xmin(),
								searchNode.rect.ymin(), searchNode.key.x(),
								searchNode.rect.ymax());
						KdNode node = new KdNode(p, searchNode.level + 1, rect);
						searchNode.left = node;
						return;
					}
					searchNode = searchNode.left;
				} else {// right
					if (searchNode.right == null) {
						RectHV rect = new RectHV(searchNode.key.x(),
								searchNode.rect.ymin(), searchNode.rect.xmax(),
								searchNode.rect.ymax());
						KdNode node = new KdNode(p, searchNode.level + 1, rect);
						searchNode.right = node;
						return;
					}
					searchNode = searchNode.right;
				}
			}
			// if level is even,then y is the key,horizontal split
			else {
				if (p.y() <= searchNode.key.y()) {// down
					if (searchNode.left == null) {
						RectHV rect = new RectHV(searchNode.rect.xmin(),
								searchNode.rect.ymin(), searchNode.rect.xmax(),
								searchNode.key.y());
						KdNode node = new KdNode(p, searchNode.level + 1, rect);
						searchNode.left = node;
						return;
					}
					searchNode = searchNode.left;
				} else {// up
					if (searchNode.right == null) {
						RectHV rect = new RectHV(searchNode.rect.xmin(),
								searchNode.key.y(), searchNode.rect.xmax(),
								searchNode.rect.ymax());
						KdNode node = new KdNode(p, searchNode.level + 1, rect);
						searchNode.right = node;
						return;
					}
					searchNode = searchNode.right;
				}
			}

		}

	}

	/**
	 * @param query
	 * @return
	 */
	// public Point2D nearest(Point2D query) {
	// List<Point2D> result = new ArrayList<Point2D>(1);
	// result.add(null);
	// // RectHV rect = new RectHV(0, 0, 1, 1);
	// searchNearest(root, query, result, Double.MAX_VALUE);
	// return result.get(0);
	// }

	public Point2D nearest(Point2D query) {
		if (root == null)
			return null;
		Stack<KdNode> stack = new Stack<KdNode>();
		stack.push(root);
		Point2D result = null;
		double distance = Double.MAX_VALUE;

		while (!stack.isEmpty()) {
			KdNode node = stack.pop();
			if (Double.compare(node.rect.distanceSquaredTo(query), distance
					* distance) >= 0) {
				continue;
			}
			if (Double.compare(node.key.distanceTo(query), distance) < 0) {
				distance = node.key.distanceTo(query);
				result = node.key;

			}

			if (node.level % 2 == 1) {
				if (query.x() <= node.key.x()) {
					if (node.right != null)
						stack.push(node.right);
					if (node.left != null)
						stack.push(node.left);

				} else {
					if (node.left != null)
						stack.push(node.left);
					if (node.right != null)
						stack.push(node.right);

				}

			} else {
				if (query.y() <= node.key.y()) {
					if (node.right != null)
						stack.push(node.right);
					if (node.left != null)
						stack.push(node.left);

				} else {
					if (node.left != null)
						stack.push(node.left);
					if (node.right != null)
						stack.push(node.right);

				}

			}

			// find the right rect

		}
		return result;
	}

	/**
	 * @param rect
	 * @return
	 */
	public Iterable<Point2D> range(RectHV rect) {
		List<Point2D> result = new ArrayList<Point2D>();
		search(root, rect, result);
		return result;
	}

	private void search(KdNode node, RectHV rect, List<Point2D> result) {
		if (node == null)
			return;
		if (rect.intersects(node.rect)) {
			if (rect.contains(node.key))
				result.add(node.key);
			search(node.left, rect, result);
			search(node.right, rect, result);
		} else {
			return;
		}
	}

	// private void searchNearest(KdNode root, Point2D query,
	// List<Point2D> result, double distance) {
	// if (root == null)
	// return;
	//
	// if (Double.compare(root.rect.distanceSquaredTo(query), distance) >= 0) {
	// return;
	// }
	//
	// if (Double.compare(root.key.distanceTo(query), distance) < 0) {
	// distance = root.key.distanceTo(query);
	// result.set(0, root.key);
	// }
	//
	// searchNearest(root.left, query, result, distance);
	// searchNearest(root.right, query, result, distance);
	//
	// }

	// private Point2D searchNearest(KdNode root, Point2D query) {
	// Point2D result = null;
	//
	// }
}
