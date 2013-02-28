import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * 2013-2-24
 * BoardTest.java
 * kenvi
 * mrwhite@163.com/kenvifly@gmail.com
 */

/**
 * @author kenvi
 * 
 */
public class BoardTest extends TestCase {
	@Test
	public void testDistance() {
		// 0 distance
		int[][] initial = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		Board bd = new Board(initial);
		assertEquals(0, bd.hamming());
		assertEquals(0, bd.manhattan());
		// 5
		initial = new int[][] { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };
		bd = new Board(initial);
		assertEquals(5, bd.hamming());
		assertEquals(10, bd.manhattan());
	}

	@Test
	public void testToString() {
		int[][] initial = new int[][] { { 1, 2 }, { 4, 5 } };
		Board bd = new Board(initial);
		String result = "1 2\n4 5\n";
		assertEquals(result, bd.toString());
		System.out.println(result);
		System.out.println(bd.toString());
	}

	@Test
	public void testIsGoal() {
		int[][] initial = new int[][] { { 1, 2 }, { 3, 0 } };
		Board bd = new Board(initial);
		assertEquals(true, bd.isGoal());

		initial = new int[][] { { 1, 0 }, { 2, 3 } };
		bd = new Board(initial);
		assertEquals(false, bd.isGoal());

		initial = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 8, 7, 0 } };
		bd = new Board(initial);
		assertEquals(false, bd.isGoal());
	}

	@Test
	public void testNb() {
		int[][] initial = new int[][] { { 1, 2 }, { 3, 0 } };
		Board bd = new Board(initial);
		List<Board> bdList = (List<Board>) bd.neighbors();
		assertEquals(2, bdList.size());
		for (Board b : bdList)
			System.out.println(b);

		initial = new int[][] { { 1, 2, 3 }, { 4, 0, 5 }, { 6, 7, 8 } };
		bd = new Board(initial);
		bdList = (List<Board>) bd.neighbors();
		assertEquals(4, bdList.size());

	}

	@Test
	public void testTwin() {
		int[][] initial = new int[][] { { 1, 2 }, { 3, 0 } };
		Board bd = new Board(initial);

		Board twin = bd.twin();
		assertEquals(bd.equals(twin), false);

		initial = new int[][] { { 2, 1 }, { 3, 0 } };
		Board result = new Board(initial);
		assertEquals(result.equals(twin), true);

	}
}
