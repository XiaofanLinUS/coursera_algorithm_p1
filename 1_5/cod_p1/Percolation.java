import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IllegalArgumentException;

public class Percolation {
	
	public Percolation(int n) {

		int last_row_first_col = n * (n - 1);
		opened = 0;

		sites = new boolean[n * n];
		//Resever 2 nodes for top and bottom
		a_union_set = new WeightedQuickUnionUF(n*n + 2);
		top = n*n;
		bottom = n*n + 1;
		
		//Connect 1st row to top node and connect last row to bot node
		for(int i = 0; i < n; i++) {
			a_union_set.union(i, top);
			a_union_set.union(last_row_first_col + i, bottom);
		}
		size = n;
		
	}
	
	
	public void display() {
		//int[][] tiles = new int[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if ( sites[ i * size + j ] ) {
					if (a_union_set.connected(i * size + j, top)) {
						System.out.print(2 + " ");
					}else {

						System.out.print(1 + " ");
					}
				}else {
					System.out.print(0 + " ");
				}
				
			}
			System.out.println();
		}
	}
	
	public void open(int row, int col) {
		if(row < 1 && row > size && col < 1 && col > size) throw new IllegalArgumentException("Out of bound");
		int i = (row - 1) * size + col - 1;
		int[] neigh = {-1, -1, -1, -1};
		
		sites[i] = true;
		opened++;
		//Get upper elem
		if(row != 1) neigh[0] = (row - 2) * size + col - 1;
		//Get right elem
		if(col != size) neigh[1] = (row - 1) * size + col;
		//Get lower elem
		if(row != size) neigh[2] = row * size + col - 1;
		//Get left elem
		if(col != 1) neigh[3] = (row - 1) * size + col -2;
		
		// 4 calls to union()
		for(int e : neigh) {
			if(e != -1 && sites[e])  {
				a_union_set.union(i, e);
			}
		}
		
	}
	
	public boolean isFull(int row, int col) {
		if(row < 1 && row > size && col < 1 && col > size) throw new IllegalArgumentException("Out of bound");
		int i = (row - 1) * size + col -1;
		// 1 call to connected()
		return a_union_set.connected(i, top) && isOpen(row, col);
		
	}
	public boolean isOpen(int row, int col) {
		if(row < 1 && row > size && col < 1 && col > size) throw new IllegalArgumentException("Out of bound");
		int i = (row - 1) * size + col - 1;

		return sites[i];
	}
	
	public int numberOfOpenSites() {
		return opened;
	}
	
	public boolean percolates() {
		return a_union_set.connected(top, bottom);
	}
	
	public static void main(String[] args) {
		int col, row;
		
		int p = 8;
		int n = 10;

		row = p / n + 1;
		col = p % n + 1;
		Percolation w = new Percolation(n);
		/**
		w.open(1, 1);
		w.open(2, 1);
		w.open(2, 2);
		w.open(3, 2);
		w.open(3, 4);
		w.open(2, 4);
		w.open(4,2);
		**/
		w.open(row, col);
		w.display();
		System.out.println(w.percolates());
		
	}
	private boolean[] sites;
	public WeightedQuickUnionUF a_union_set ;
	private int size;
	private int top;
	private int bottom;
	private int opened;
}
