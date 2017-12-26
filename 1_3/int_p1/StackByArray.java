import java.util.Iterator;

class StackByArray<Item> implements Iterable<Item> {
	private Item[] items;
	private int size;
	private final int scaleFactor = 2;

	public StackByArray() {
		items = (Item[]) new Object[1];
		size = 0;
	}

	public void push(Item i) {
		items[size++] = i;
		if (size == items.length)
			enlarge();

	}


    
	public Item pop() {
		Item theItem = items[--size];
		// Decrease the size and get the last item
		items[size] = null;
		// Nullify the last room to ensure no lolitering
		if (size == items.length / (scaleFactor * scaleFactor))
			shrink();
		return theItem;

	}

	private void shrink() {
		Object[] shrinked = new Object[items.length / scaleFactor];
		// Copy elements from the old home to the new home
		for (int i = 0; i < size; i++) {
			shrinked[i] = items[i];
		}
		items = (Item[]) shrinked;

	}

	private void enlarge() {
		Object[] expanded = new Object[items.length * scaleFactor];
		// Copy elements from the old home to the new home
		for (int i = 0; i < size; i++) {
			expanded[i] = items[i];
		}
		items = (Item[]) expanded;
	}

	public static void main(String[] args) {
	    StackByArray<String> s = new StackByArray<>();
	    s.push("1");
	    s.push("2");
	    s.push("3");


	    for(String a : s) {
		System.out.println(a);
	    }

	    int size = s.size;
	    for(int i = 0; i < size; i++) {
		System.out.println(s.pop());
	    }

	}

    
	@Override
	public Iterator<Item> iterator() {
	    return new StackIterator();
	}

	private class StackIterator implements Iterator<Item> {
	int current = size;
	
		@Override
		public boolean hasNext() {
			return current !=0;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			return items[--current];
		}

	}
}
