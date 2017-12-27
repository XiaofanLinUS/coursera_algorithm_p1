import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class DutchFlag {
	private int[] buckets;
	private int size;

	public DutchFlag(int numb) {
		size = numb;
		buckets = new int[numb];
		StdOut.println("Initialize: ");
		for (int i = 0; i < numb; i++) {
			buckets[i] = StdRandom.uniform(3);
			StdOut.println(buckets[i]);
		}

	}

	public void sort() {
		int i = 0;
		int j = size - 1;
		int k = 0;
		while (k < j) {
			if (buckets[k] == 0) {
				swap(i, k);
				i++;
				k++;
			}
			if (buckets[k] == 1) {
				k++;
			}
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
		DutchFlag df = new DutchFlag(5);
		df.sort();
		StdOut.println("Sorted: ");
		for (int e : df.buckets) {
			StdOut.println(e);
		}
	}
}
