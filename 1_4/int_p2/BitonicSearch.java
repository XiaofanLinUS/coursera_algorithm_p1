class BitonicSearch {

    public static void main(String[] args) {
	int[] a = {-3, 9, 8, 20, 17, 5, 1};
	int m = middleLine(a);
	System.out.println(m);
	


    }


    public static int middleLine(int[] arr) {
	int l = 0, h = arr.length;
	int m = l + (h - l) / 2;
	//System.out.println(m);
	boolean LL = false;
	boolean LR = false;

	int n = 0;
	while(n <= 10000) {
	    LL = (arr[m] > arr[m-1]);
	    LR = (arr[m] > arr[m+1]);
	    if((LR & LL)||(!LR & !LL)) {
		break;
	    }
	    else if(!LR) {
		l = m;
       	    }else {
		h = m;
	    }
	    m = l + (h-l)/2;
	    //System.out.println(m);
	    n ++;
	}
	    
	return LR ? m : m - 1;
    }
}
