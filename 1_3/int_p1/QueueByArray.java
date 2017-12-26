class QueueByArray<Item> {
	private Item[] items;
	private int head, tail;
	private final int ScaleFactor = 2;

    	public static void main(String[] args) {
	    QueueByArray<String> q = new QueueByArray<>();

	    for(int i = 0; i < 50; i++) {
		q.enqueue(Integer.toString(i));
		Object[] stuffs = (Object[]) q.items;		
		System.out.println("Size: " + stuffs.length);
	    }


	    int size = q.tail - q.head;
	    for(int i = 0; i < size; i++) {
		System.out.println(q.dequeue());
		Object[] stuffs = (Object[]) q.items;		
		System.out.println("Size: " + stuffs.length);

	    }

	}

	public QueueByArray() {
		items = (Item[]) new Object[1];
		head = 0;
		tail = 0;
	}

	public void enqueue(Item i) {
		items[tail++] = i;
		if (tail >= items.length)
			adjust();
	}

	public Item dequeue() {
		Item theItem = items[head++];
		if (head >= items.length * (1 - 1.0 / (ScaleFactor * ScaleFactor)))
			adjust();
		return theItem;
	}

	private void adjust() {
		Object[] newPlace;

		int size = tail - head;
		if (size >= items.length) {
			newPlace = new Object[items.length * ScaleFactor];
		} else if (size <= items.length / (ScaleFactor * ScaleFactor)) {
			newPlace = new Object[items.length / ScaleFactor];
		} else {
			newPlace = new Object[items.length];
		}
		// Rebase the index
		int newTail = tail - head;

		for (int i = 0; i < newTail; i++) {
			newPlace[i] = items[head + i];
		}
		items = (Item[]) newPlace;
		head = 0;
		tail = newTail;
	}
}
