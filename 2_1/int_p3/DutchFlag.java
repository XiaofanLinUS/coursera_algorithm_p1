import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class DutchFlag {
	private int[] buckets = { 2, 0, 1, 2, 1, 2, 0, 0, 2, 1 };

	private int size = 10;

	public DutchFlag(int numb) {
		size = numb;
		buckets = new int[numb];
		StdOut.println("Initialize: ");
		for (int i = 0; i < numb; i++) {
			buckets[i] = StdRandom.uniform(3);
			StdOut.println(buckets[i]);
		}

	}

	public DutchFlag() {

	}

	public void sort() {
		int i = 0;
		int j = size - 1;
		int k = 0;

		/**
		 * From 0 to i - 1, buckets contain redballs. From i to k - 1, buckets contain
		 * whiteballs. From k to j - 1, buckets are unexamined. From j - 1 to size - 1,
		 * buckets contain blueballs.
		 **/

		while (k <= j) {
			if (buckets[k] == 0) {
				swap(i, k);
				i++;
				k++;
			} else

			if (buckets[k] == 1) {
				k++;
			} else

			if (buckets[k] == 2) {
				swap(j, k);
				j--;
			}
		}

	}

	public void swap(int i, int j) {
		int tmp = buckets[i];
		buckets[i] = buckets[j];
		buckets[j] = tmp;
	}

	public static void main(String[] args) {
		DutchFlag df = new DutchFlag();
		df.sort();
		StdOut.println("Sorted: ");
		for (int e : df.buckets) {
			StdOut.println(e);
		}
	}
}
