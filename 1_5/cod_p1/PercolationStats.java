import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	public PercolationStats(int n, int trials) {

		int p;
		int col = -1, row = -1;
		int size = n * n;
		int[] randomSites = new int[size];
		
		t = trials;
		//How many successes are needed to perolate
		success = new double[trials];
		
		mean = -1;
		stddev = -1;
		confidenceHi = -1;
		confidenceLo = -1;
		
		for(int i = 0; i < size; i++) {
			randomSites[i] = i;
		}
		
		for(int i = 0; i < trials; i++) {

			//Reintialize index p and grid
			//Shuffle the random sites
			
			p = 0;
			grid = new Percolation(n);
			StdRandom.shuffle(randomSites);
			
			while(!grid.percolates()) {
				success[i]++;
				
				row = randomSites[p] / n + 1;
				col = randomSites[p] % n + 1;
				grid.open(row, col);
				p++;
			}
			success[i] /= size;
		}
	}
	
	public double mean() {
		if(mean == -1.0) mean = StdStats.mean(success);
		return mean;
	}
	
	public double stddev() {
		if(stddev == -1.0) stddev = StdStats.stddev(success);
		return stddev;
	}
	
	public double confidenceLo() {
		if(confidenceLo == -1) confidenceLo = mean() - 1.96 * stddev() / Math.sqrt(t);
		return confidenceLo;
	}
	

	public double confidenceHi() {
		if(confidenceHi == -1) confidenceHi = mean() + 1.96 * stddev() / Math.sqrt(t);
		return confidenceHi;
	}
	
	
	public static void main(String[] args) {
		PercolationStats test = new PercolationStats(200, 100);
		System.out.println(test);
		
	}
	
	public String toString() {
		String a = "mean                    = ";
		a += this.mean() + "\n";
		a += "stddev                  = ";
		a += this.stddev() + "\n";
		a += "95% confidence interval = [";
		a += this.confidenceLo() + ", " + this.confidenceHi() + "]\n";
		
		return a;
	}
	
	
	private Percolation grid;
	private int t;
	private double[] success;
	private double mean, stddev, confidenceLo, confidenceHi;
}
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    
    public PercolationStats(int n, int trials) {
        int p;
        int col = -1, row = -1;
        int size = n * n;
        int[] randomSites = new int[size];

        t = trials;
        // How many successes are needed to perolate
        success = new double[trials];

        mean = -1;
        stddev = -1;
        confidenceHi = -1;
        confidenceLo = -1;

        for (int i = 0; i < size; i++) {
            randomSites[i] = i;
        }

        for (int i = 0; i < trials; i++) {

            // Reintialize index p and grid
            // Shuffle the random sites

            p = 0;
            grid = new Percolation(n);

            StdRandom.shuffle(randomSites);

            while (!grid.percolates()) {
                success[i]++;

                row = randomSites[p] / n + 1;
                col = randomSites[p] % n + 1;
                grid.open(row, col);

                p++;
            }
            success[i] /= size;
        }
    }

    
    public double mean() {
        if (mean == -1.0)
            mean = StdStats.mean(success);
        return mean;
    }

    
    public double stddev() {
        if (stddev == -1.0)
            stddev = StdStats.stddev(success);
        return stddev;
    }
    

    public double confidenceLo() {
        if (confidenceLo == -1)
            confidenceLo = mean() - 1.96 * stddev() / Math.sqrt(t);
        return confidenceLo;
    }

    
    public double confidenceHi() {
        if (confidenceHi == -1)
            confidenceHi = mean() + 1.96 * stddev() / Math.sqrt(t);
        return confidenceHi;
    }
    

    public static void main(String[] args) {

        
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        
        /**double time1 = 0, time2 = 0, cost = 0;
        Stopwatch timer = new Stopwatch();


        for (int i = n; i <= 1600; i *= 2) {

            PercolationStats test = new PercolationStats(i, t);

            time1 = time2;
            time2 = timer.elapsedTime();
            cost = time2 - time1;

            System.out.printf("size: %-5d| cost: %.3f \n", i, cost);
        }

        n = 100;
        timer = new Stopwatch();
        time1 = 0;
        time2 = 0;
        cost = 0;

        for (int i = n; i <= 1600; i *= 2) {

            PercolationStats test = new PercolationStats(i, t);

            time1 = time2;
            time2 = timer.elapsedTime();
            cost = time2 - time1;

            System.out.printf("trial: %-5d| cost: %.3f \n", i, cost);
        }
        **/


        PercolationStats test = new PercolationStats(n, t);
        System.out.println(test);
        
    }

    
    public String toString() {
        String a = "mean                    = ";
        a += this.mean() + "\n";
        a += "stddev                  = ";
        a += this.stddev() + "\n";
        a += "95% confidence interval = [";
        a += this.confidenceLo() + ", " + this.confidenceHi() + "]\n";

        return a;
    }

    private Percolation grid;
    private int t;
    private double[] success;
    private double mean, stddev, confidenceLo, confidenceHi;
}
