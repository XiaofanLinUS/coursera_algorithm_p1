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
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	public PercolationStats(int n, int trials) {

		int p = -1;
		int col = -1, row = -1;
		int size = n * n;
		
		t = trials; 
		grid = new Percolation(n);
		success = new double[trials];
		
		mean = -1;
		stddev = -1;
		confidenceHi = -1;
		confidenceLo = -1;
		for(int i = 0; i < trials; i++) {
			
			while(!grid.percolates()) {
				success[i]++;
				p = (int) (StdRandom.uniform() * size);
				row = p / n + 1;
				col = p % n + 1;
				grid.open(row, col);
			}
			success[i] /= size;
			System.out.println(success[i]);
			
		}
	}
	
	public double mean() {
		if(mean == -1.0) mean = StdStats.mean(success);
		return mean;
	}
	
	public double stddev() {
		if(stddev == -1) stddev = StdStats.stddev(success);
		return stddev;
	}
	
	public double confidenceLo() {
		if(confidenceLo == -1) confidenceLo = mean() - 1.96 * stddev() / Math.sqrt(t);
		return confidenceLo;
	}
	

	public double confidenceHi() {
		if(confidenceHi == -1) confidenceHi = mean() + 1.96 * stddev() / Math.sqrt(t);
		return confidenceLo;
	}
	
	
	public static void main(String[] args) {
		PercolationStats test = new PercolationStats(200, 100);
		System.out.println(test.mean);
		
	}
	
	
	private Percolation grid;
	private int t;
	private double[] success;
	private double mean, stddev, confidenceLo, confidenceHi;
}
