import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * 2013-2-18
 * GrahamTest.java
 * kenvi
 * mrwhite@163.com/kenvifly@gmail.com
 */

/**
 * @author kenvi
 * 
 */
public class GrahamTest extends TestCase {

	@Test
	public void testInput1() throws Exception {
		System.out.println("---result---");
		Graham graham = new Graham(
				getPoints("E:\\git\\Algorithm\\Algorithm\\src\\grahm-input1"));
		// graham.dumpPoints();
		graham.findSolution();
	}

	@Test
	public void testInput2() throws Exception {
		System.out.println("---result---");
		Graham graham = new Graham(
				getPoints("E:\\git\\Algorithm\\Algorithm\\src\\grahm-input2"));
		graham.findSolution();
	}

	@Test
	public void testInput3() throws Exception {
		System.out.println("---result---");
		Graham graham = new Graham(
				getPoints("E:\\git\\Algorithm\\Algorithm\\src\\grahm-input3"));
		graham.findSolution();
	}

	@Test
	public void testInput4() throws Exception {
		System.out.println("---result---");
		Graham graham = new Graham(
				getPoints("E:\\git\\Algorithm\\Algorithm\\src\\grahm-input4"));
		graham.findSolution();
	}

	private Point2D[] getPoints(String file) throws Exception {
		List<Point2D> points = new ArrayList<Point2D>();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)));
		String line = null;
		while ((line = br.readLine()) != null) {
			Point2D p = new Point2D(line.split(" ")[0], Double.parseDouble(line
					.split(" ")[1]), Double.parseDouble(line.split(" ")[2]));
			points.add(p);
		}
		br.close();
		return points.toArray(new Point2D[0]);
	}

	@Test
	public void testComparatorLT() {
		Point2D d = new Point2D("D", 1.0, 0.0);
		Point2D a = new Point2D("A", 6.0, 2.0);
		Point2D g = new Point2D("G", 8.0, 3.0);
		assertEquals(-1, d.POLOR_ORDER.compare(a, g));

	}

	@Test
	public void testComparatorGT() {
		Point2D d = new Point2D("D", 1.0, 0.0);
		Point2D a = new Point2D("A", 6.0, 2.0);
		Point2D g = new Point2D("G", 8.0, 3.0);
		assertEquals(1, d.POLOR_ORDER.compare(g, a));

	}

	@Test
	public void testSort() {
		Point2D d = new Point2D("D", 1.0, 0.0);
		Point2D a = new Point2D("A", 6.0, 2.0);
		Point2D g = new Point2D("G", 8.0, 3.0);
		Point2D[] ps = new Point2D[] { a, d, g };
		d.sort(ps);
		for (Point2D p : ps) {
			System.out.println(p.getName());
		}
	}
}
