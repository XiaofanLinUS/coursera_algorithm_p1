public class LinearSum {

	public static void main(String[] args) {
		int[] nums = { 10, 20, 30, 40, 50 };
		int n = countSums(nums, 70);
		System.out.println(n);
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
