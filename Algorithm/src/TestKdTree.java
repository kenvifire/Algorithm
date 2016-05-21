
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * 2013-3-16
 * TestKdTree.java
 * kenvi
 * mrwhite@163.com/kenvifly@gmail.com
 */

/**
 * @author kenvi
 * 
 */
public class TestKdTree extends TestCase {
	@Test
	public void testInsert() {
		StdDraw.show(0);
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(0.5, 0.5));
		kdTree.insert(new Point2D(0.8, 0.6));
		kdTree.insert(new Point2D(0.9, 0.9));
		kdTree.insert(new Point2D(0.3, 0.3));
		kdTree.insert(new Point2D(0.2, 0.2));
		kdTree.insert(new Point2D(0.1, 0.1));
		kdTree.insert(new Point2D(0.1, 0.8));
		kdTree.draw();
		StdDraw.show(0);
		while (true)
			;

	}

	@Test
	public void testNearest() {

		KdTree kdTree = new KdTree();
		// kdTree.insert(new Point2D(0.5, 0.6));
		// kdTree.insert(new Point2D(0.7, 0.8));
		// kdTree.insert(new Point2D(0.9, 1.0));
		// kdTree.insert(new Point2D(0.3, 0.3));
		// kdTree.insert(new Point2D(0.5, 0.6));
		// kdTree.insert(new Point2D(0.4, 0.7));
		kdTree.insert(new Point2D(0.3, 0.2));
		kdTree.insert(new Point2D(0.8, 0.45));
		kdTree.insert(new Point2D(0.67, 0.32));
		kdTree.insert(new Point2D(0.23, 0.78));

		PointSET ps = new PointSET();
		ps.insert(new Point2D(0.3, 0.2));
		ps.insert(new Point2D(0.8, 0.45));
		ps.insert(new Point2D(0.67, 0.32));
		ps.insert(new Point2D(0.23, 0.78));

		System.out.println(kdTree.nearest(new Point2D(0.43339843750000007,
				0.7427734375)));
		System.out.println(ps.nearest(new Point2D(0.43339843750000007,
				0.7427734375)));

		// Point2D result = null;
		// test(result);
		// assertNotNull(result);

	}

	private void test(Point2D result) {
		result = new Point2D(2, 2);
	}

	@Test
	public void testRangeSearch() {
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(0.5, 0.5));
		// assertEquals(1, ((List) kdTree.range(new RectHV(0, 0, 1,
		// 1))).size());
		// assertEquals(0,
		// ((List) kdTree.range(new RectHV(0, 0, 0.4, 0.4))).size());
		kdTree.insert(new Point2D(0.6, 0.6));
		kdTree.insert(new Point2D(0.4, 0.4));
		// assertEquals(3,
		// ((List) kdTree.range(new RectHV(0.4, 0.4, 0.6, 0.6))).size());
		assertEquals(1,
				((List) kdTree.range(new RectHV(0.55, 0.55, 0.6, 0.6))).size());
	}

	@Test
	public void testContains() {
		// StdDraw.show(0);
		KdTree kdTree = new KdTree();
		assertEquals(true, kdTree.isEmpty());
		kdTree.insert(new Point2D(0.5, 0.5));
		kdTree.insert(new Point2D(0.8, 0.6));
		kdTree.insert(new Point2D(0.9, 0.9));
		kdTree.insert(new Point2D(0.3, 0.3));
		kdTree.insert(new Point2D(0.2, 0.2));
		kdTree.insert(new Point2D(0.1, 0.1));
		kdTree.insert(new Point2D(0.1, 0.8));
		// kdTree.draw();
		// StdDraw.show(0);
		// while (true)
		// ;
		assertEquals(true, kdTree.contains(new Point2D(0.1, 0.8)));
		assertEquals(false, kdTree.contains(new Point2D(0.88, 0.88)));

		assertEquals(7, kdTree.size());

		assertEquals(false, kdTree.isEmpty());
		kdTree.insert(new Point2D(0.1, 0.8));
		assertEquals(7, kdTree.size());

	}

	@Test
	// Test 6a: Insert N distinct points and call nearest() with random query
	// points
	public void testCorrectness() {
		// 100000 random points in 100000-by-100000 grid
		String filename = "100k.txt";
		In in = new In(filename);

		// StdDraw.show(0);

		// initialize the two data structures with point from standard input
		PointSET brute = new PointSET();
		KdTree kdtree = new KdTree();
		while (!in.isEmpty()) {
			double x = in.readDouble();
			double y = in.readDouble();
			Point2D p = new Point2D(x, y);
			kdtree.insert(p);
			brute.insert(p);
		}

		int count = 1;
		while (count < 1000) {
			Point2D point = new Point2D(StdRandom.random(), StdRandom.random());
			kdtree.nearest(point);
			assertEquals(true,
					brute.nearest(point).equals(kdtree.nearest(point)));
			count++;

		}
	}
}
