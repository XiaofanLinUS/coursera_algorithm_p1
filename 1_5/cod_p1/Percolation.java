import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IllegalArgumentException;

public class Percolation {
	
	public Percolation(int n) {

		int last_row_first_col = n * (n - 1);
		opened = 0;

		sites = new boolean[n];
		//Resever 2 nodes for top and bottom
		a_union_set = new WeightedQuickUnionUF(n*n + 2);
		top = n*n;
		bottom = n*n + 1;
		
		//Connect 1st row to top node and connect last row to bot node
		for(int i = 0; i < n; i++) {
			a_union_set.connected(i, top);
			a_union_set.connected(last_row_first_col + i, bottom);
		}
		size = n;		
		
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
		
		for(int e : neigh) {
			if(e != -1 && sites[e]) a_union_set.connected(i, e);
		}
		
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
	

	private boolean[] sites;
	private WeightedQuickUnionUF a_union_set ;
	private int size;
	private int top;
	private int bottom;
	private int opened;
}
