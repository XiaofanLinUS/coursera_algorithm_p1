class BitonicSearch {

	public static void main(String[] args) {
		int[] a = { -3, 8, 9, 20, 17, 5, 1 };
		//int[] b = { -3, 8, 9, 20};
		int key = bitonicSearch(9, a);
		System.out.println(key);

	}

	public static int bitonicSearch(int val, int[] a) {
		int key = -1;
		int maxy = middleLine(a);

		int l1 = 0, h1 = maxy, l2 = maxy + 1, h2 = a.length - 1;

		key = binarySearch(val, a, l1, h1);
		if (key != -1)
			return key;
		key = binarySearch(val, a, l2, h2);
		return key;
	}

	public static int binarySearch(int val, int[] a, int l, int h) {
		// l >= 0 and h < arr.length!!!
		int key = -1;
		int m;
		
		while (l < h) {
			m = l + (h - l) / 2;
			System.out.println(l + " " + m + " " + h);
		    
			if (val <= a[m]) {
				// make sure val is possibly equal to a[m]
				// otherwise, l = m + 1 will skip m. (Disaster)
			    h = m;
			} else {
				l = m + 1;
			}
		
		}

		if (a[l] == val)
			key = l;

		return key;
	}

	public static int middleLine(int[] arr) {
		int l = 0;
		int h = arr.length - 1;
		int m = l + (h - l) / 2;

		boolean LL = false;
		boolean LR = false;
		int n = 0;
		while (n < 1000) {
			LL = (arr[m] > arr[m - 1]);
			LR = (arr[m] > arr[m + 1]);

			
			if ((LR & LL) || (!LR & !LL)) {
				break;
			} else if (!LR) {
				l = m;
			} else {
				h = m;
			}
			m = l + (h - l) / 2;
			n++;
		}
		return LR ? m : m - 1;
	}
}
