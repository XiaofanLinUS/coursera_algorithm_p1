import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.lang.IllegalArgumentException;

public class Percolation {

	public Percolation(int n) {
		if (n <= 0)
			throw new IllegalArgumentException("Invalid Value n");
		// int last_row_first_col = n * (n - 1);
		opened = 0;

		sites = new int[n * n];
		// Resever 2 nodes for top and bottom
		a_union_set = new WeightedQuickUnionUF(n * n);
		// top = n * n;
		// bottom = n * n + 1;
		// Connect 1st row to top node and connect last row to bot node
		// for (int i = 0; i < n; i++) {
		// a_union_set.union(i, top);
		// a_union_set.union(last_row_first_col + i, bottom);
		// }

		size = n;

		perlocated = false;

	}

	private int xyToIndex(int row, int col) {
		int index = (row - 1) * size + col - 1;
		return index;
	}

	public void open(int row, int col) {
		boolean topConnected = false;
		boolean bottomConnected = false;
		if (row < 1 || row > size || col < 1 || col > size)
			throw new IllegalArgumentException("Out of bound");
		int i = a_union_set.find(xyToIndex(row, col));
		int[] neigh = { -1, -1, -1, -1 };
		// If the site is not open, then open it else leave it alone
		if (sites[i] == 0) {
			opened++;
			
			if (row == size) {
				sites[i] = 3;
				bottomConnected = true;
			}

			if (row == 1) {
				sites[i] = 2;
				topConnected = true;

			} else if(row != size && row != 1){
				sites[i] = 1;
			}

		} else {
			return;
		}

		// 1 means open, 2 means top-connected, 3 means bottom-connected
		// Get upper elem
		if (row != 1) {
			neigh[0] = a_union_set.find(xyToIndex(row - 1, col));
			if (sites[neigh[0]] == 2)
				topConnected = true;

			if (sites[neigh[0]] == 3)
				bottomConnected = true;
		}

		// Get right elem
		if (col != size) {
			neigh[1] = a_union_set.find(i + 1);
			if (sites[neigh[1]] == 2)
				topConnected = true;

			if (sites[neigh[1]] == 3)
				bottomConnected = true;
		}

		// Get lower elem
		if (row != size) {
			neigh[2] = a_union_set.find(xyToIndex(row + 1, col));
			if (sites[neigh[2]] == 2)
				topConnected = true;

			if (sites[neigh[2]] == 3)
				bottomConnected = true;
		}
		// Get left elem
		if (col != 1) {
			neigh[3] = a_union_set.find(i - 1);
			if (sites[neigh[3]] == 2)
				topConnected = true;

			if (sites[neigh[3]] == 3)
				bottomConnected = true;
		}


		if (topConnected && bottomConnected)
			perlocated = true;

		// 4 calls to union()
		for (int e : neigh) {
			if (e != -1 && sites[e] != 0) {

				if (topConnected) {
					sites[e] = 2;
					sites[i] = 2;
				} else if (bottomConnected) {
					sites[e] = 3;
					sites[i] = 3;
				}

				a_union_set.union(e, i);
			}
		}

	}

	public boolean isFull(int row, int col) {
		if (row < 1 || row > size || col < 1 || col > size)
			throw new IllegalArgumentException("Out of bound");
		// int i = (row - 1) * size + col - 1;
		// 1 call to connected()
		return sites[a_union_set.find(xyToIndex(row, col))] == 2;

	}

	public boolean isOpen(int row, int col) {
		if (row < 1 || row > size || col < 1 || col > size)
			throw new IllegalArgumentException("Out of bound");
		return sites[xyToIndex(row, col)] != 0;
	}

	public int numberOfOpenSites() {
		return opened;
	}

	public boolean percolates() {
		return perlocated;
	}

	public static void main(String[] args) {
		Percolation p = new Percolation(1);

		System.out.println(p.percolates());

	}

	private int[] sites;
	private WeightedQuickUnionUF a_union_set;
	private int size;
	// private int top;
	// private int bottom;
	private int opened;
	private boolean perlocated;
}
