public class QuadraticThreeSums {

	public static void main(String[] args) {
		int[] nums = { -20, -10, 0, 10, 20, 30 };
		int n = countThreeSums(nums);
		System.out.println(n);
		n = slowCountThreeSums(nums);
		System.out.println(n);
	}


    public static int slowCountThreeSums(int[] a) {
	int count = 0;
	for(int i = 0; i < a.length - 2; i++) {
	    for(int j = i + 1; j < a.length - 1; j++) {
		for(int k = j + 1; k < a.length; k++) {
		    if(a[i] + a[j] + a[k] == 0) {
			count++;
		    }
		}
	    }
	}
	return count;


    }
	public static int countThreeSums(int[] a) {
		int count = 0;
		for (int i : a) {
			count += countSums(a, -i);

		}
		return count;
	}

	public static int countSums(int[] a, int goal) {
		int count = 0;
		int i = 0, j = a.length - 1;
		while (i != j) {
			if (a[i] + a[j] > goal) {
				j--;
			} else if (a[i] + a[j] < goal) {
				break;
			} else {
				count++;
				i++;
			}
		}
		return count;
	}

}
