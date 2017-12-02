public class LinearSum {

	public static void main(String[] args) {
		int[] nums = { 10, 20, 30, 40, 50 };
		int n = countSums(nums, 20);
		System.out.println(n);
	}

	public static int countSums(int[] a, int goal) {
		int count = 0;
		int i = 0, j = 1;
		while (i != j && j < a.length) {
			if (a[i] + a[j] < goal) {
				j++;
			} else if (a[i] + a[j] > goal) {
				i++;
			} else {
				count++;
				i++;
				j = i+1;
			}
		}
		return count;
	}

}
