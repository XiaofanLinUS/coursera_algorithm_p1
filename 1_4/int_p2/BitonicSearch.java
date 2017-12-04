class BitonicSearch {

	public static void main(String[] args) {
		int[] a = { -3, 9, 8, 20, 17, 5, 1 };
		int m = middleLine(a);
		System.out.println(m);

	}

	public static int binarySearch(int val, int[] a, int l, int h) {
		// l >= 0 and h < arr.length!!!
		int key = -1;
		int m;

		while (l < h) {
			m = l + (h - l) / 2;
			if (val <= a[m]) {
				// make sure val is possibly equal to a[m]
				// otherwise, l = m + 1 will skip m. (Disaster)
				m = h;
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
	    int h = arr.length;
	    int m = l + (h - l) / 2;


	    boolean LL = false;
	    boolean LR = false;


	    while(l < h) {
		LL = (arr[m] > arr[m-1]);
		LR = (arr[m] > arr[m+1]);

		if((LR & LL)||(!LR & !LL)) {
		    break;
		}else if(!LR) {
		    l = m;
		}else {
		    h = m;
		}
		m = l + (h-l)/2;
	    }
	    return LR ? m : m - 1;
	}
}
