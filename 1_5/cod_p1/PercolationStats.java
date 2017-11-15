import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	public PercolationStats(int n, int trials) {
		grid = new Percolation(n);
		success = new double[trials];
		int p = -1;
		int col = -1, row = -1;
		int size = n * n;
		
		for(int i = 0; i < trials; i++) {
			
			while(!grid.percolates()) {
				success[i]++;
				p = (int) (StdRandom.uniform() * size);
				row = p / n + 1;
				col = p % n + 1;
				grid.open(row, col);
			}
			success[i] /= size;
			
		}
	}
	
	public double mean() {
		mean = StdStats.mean(success);
		return mean;
	}
	
	
	
	
	
	
	private Percolation grid;
	private double[] success;
	private double mean, stddev, confidenceLo, confidenceHi;
}
