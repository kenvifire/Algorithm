import java.util.ArrayList;
import java.util.List;

/**
 * 2013-2-24
 * Board.java
 * kenvi
 * mrwhite@163.com/kenvifly@gmail.com
 */

/**
 * @author kenvi
 * 
 */
public class Board {
	private int[][] blocks;
	private int N;

	// construct a board from an N-by-N array of blocks
	// (where blocks[i][j] = block in row i, column j)
	public Board(int[][] initial) {
		this.N = initial.length;
		blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				this.blocks[i][j] = initial[i][j];

	}

	public int get(int i, int j) {
		return blocks[i][j];
	}

	// board dimension N
	public int dimension() {
		return this.N;
	}

	// number of blocks out of place
	public int hamming() {
		int distance = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (blocks[i][j] != 0 && blocks[i][j] != (i * N + j + 1)) {
					distance++;
				}
			}
		}
		return distance;
	}

	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int distance = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (blocks[i][j] != 0) {
					int num = blocks[i][j];

					int goalx = (num - 1) / N;
					int goaly = (num - 1) % N;

					distance += (Math.abs(goaly - j) + Math.abs(goalx - i));

					// distance += Math.abs(goal - num);
					// System.out.println(num + ","
					// + (Math.abs(goaly - i) + Math.abs(goalx - j)) + ":"
					// + goalx + "," + goaly);
				}
			}
		}
		return distance;
	}

	// is this board the goal board?
	public boolean isGoal() {
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < N; j++) {
				if (blocks[i][j] != (i * N + j + 1))
					return false;
			}
		}

		for (int j = 0; j < N - 1; j++) {
			if (blocks[N - 1][j] != ((N - 1) * N + j + 1))
				return false;
		}

		return true;
	}

	// a board obtained by exchanging two adjacent blocks in the same row
	public Board twin() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - 1; j++) {
				if (blocks[i][j] != 0 && blocks[i][j + 1] != 0) {
					Board twin = new Board(this.blocks);
					twin.swap(i, j, i, j + 1);
					return twin;
				}
			}
		}

		return null;
	}

	// does this board equal y?
	public boolean equals(Object y) {
		if (y == null)
			return false;
		if (y instanceof Board) {
			Board that = (Board) y;
			if (this.N != that.N)
				return false;
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					if (blocks[i][j] != that.blocks[i][j])
						return false;
			return true;
		} else {
			return false;
		}

	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		List<Board> neighborList = new ArrayList<Board>();
		int x = 0;
		int y = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				if (blocks[i][j] == 0) {
					x = i;
					y = j;
					// jump out
					i = N + 1;
					j = N + 1;
				}
			}
		// top
		if (x > 0) {
			Board nb = new Board(this.blocks);
			nb.swap(x, y, x - 1, y);
			neighborList.add(nb);
		}

		// down
		if (x < N - 1) {
			Board nb = new Board(this.blocks);
			nb.swap(x, y, x + 1, y);
			neighborList.add(nb);
		}

		// left
		if (y > 0) {
			Board nb = new Board(this.blocks);
			nb.swap(x, y, x, y - 1);
			neighborList.add(nb);
		}
		// right
		if (y < N - 1) {
			Board nb = new Board(this.blocks);
			nb.swap(x, y, x, y + 1);
			neighborList.add(nb);
		}
		return neighborList;
	}

	private void swap(int x1, int y1, int x2, int y2) {
		int temp = blocks[x1][y1];
		blocks[x1][y1] = blocks[x2][y2];
		blocks[x2][y2] = temp;

	}

	// string representation of the board (in the output format specified below)
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.N + "\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - 1; j++) {
				sb.append(this.blocks[i][j] + " ");
			}
			sb.append(this.blocks[i][N - 1] + "\n");
		}
		return sb.toString();
	}
}
