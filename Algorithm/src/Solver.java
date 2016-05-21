import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 2013-2-24
 * Solver.java
 * kenvi
 * mrwhite@163.com/kenvifly@gmail.com
 */

/**
 * @author kenvi
 * 
 */
public class Solver {
	private MinPQ<TreeNode> minPQ;
	private int N;

	private class TreeNode {
		int moves;
		Board board;
		TreeNode prev;

		public TreeNode(int moves, Board board, TreeNode prev) {
			N = board.dimension();
			this.moves = moves;
			this.board = board;
			this.prev = prev;

		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					sb.append(board.get(i, j));

			return sb.toString();
		}
	}

	private void sovle() {
		//
		TreeNode searchNode = null;
		while (!minPQ.isEmpty() && !isSolvable()) {
			searchNode = minPQ.delMin();
			TreeNode prev = searchNode.prev;
			for (Board bd : searchNode.board.neighbors()) {
				int moves = 1;
				if (prev != null) {
					moves = prev.moves + 1;
					System.out.println(moves);
				}
				TreeNode node = new TreeNode(moves, bd, searchNode);
				if (prev == null) {
					minPQ.insert(node);
					// System.out.println(node);
				} else if (prev != null) {

					boolean tag = false;
					while (prev != null) {
						// System.out.println("checking...");
						if (prev.board.equals(bd)) {
							tag = true;
							break;
						}
						prev = prev.prev;
					}
					if (!tag) {
						minPQ.insert(node);
						// System.out.println(node);
					}
					// System.out.println("check done");
				}

			}
		}

	}

	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		TreeNode initialNode = new TreeNode(0, initial, null);
		minPQ = new MinPQ<Solver.TreeNode>(N, new BoardComparator());
		minPQ.insert(initialNode);

		// solve
		sovle();
	}

	// is the initial board solvable?
	public boolean isSolvable() {
		if (minPQ.isEmpty())
			return false;
		Board best = minPQ.min().board;
		if (best.isGoal())
			return true;
		return false;

	}

	// min number of moves to solve initial board; -1 if no solution
	public int moves() {
		if (minPQ.isEmpty())
			return -1;
		TreeNode best = minPQ.min();
		if (best.board.isGoal())
			return ((List) solution()).size() - 1;
		return -1;

	}

	// sequence of boards in a shortest solution; null if no solution
	public Iterable<Board> solution() {
		if (minPQ.isEmpty() || isSolvable() == false)
			return null;
		List<Board> solutions = new ArrayList<Board>();
		TreeNode root = minPQ.min();

		while (root != null) {
			solutions.add(root.board);
			root = root.prev;
		}

		Collections.reverse(solutions);
		return solutions;
	}

	// solve a slider puzzle (given below)
	public static void main(String[] args) {
		// create initial board from file
		In in = new In(args[0]);
		int N = in.readInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		// if (!solver.isSolvable())
		// StdOut.println("No solution possible");
		// else {
		// StdOut.println("Minimum number of moves = " + solver.moves());
		// for (Board board : solver.solution())
		// StdOut.println(board);
		// }
	}

	private class BoardComparator implements Comparator<TreeNode> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(TreeNode o1, TreeNode o2) {
			int p1 = o1.board.manhattan() + o1.moves;
			int p2 = o2.board.manhattan() + o2.moves;
			return p1 == p2 ? 0 : (p1 > p2 ? 1 : -1);
		}
	}

}
