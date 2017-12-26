import java.util.Iterator;

class Queue<Item> implements Iterable<Item> {

	public static void main(String[] args) {
		Queue<String> q = new Queue<>();
		q.push("1");
		q.push("2");
		q.push("3");
                
		for (String a : q) {
			System.out.println(a);
		}

		q.pop();

		
		for (String a : q) {
			System.out.println(a);
		}

		q.pop();
		q.pop();
		
		for (String a : q) {
			System.out.println(a);
		}
	}

    private class Node {
		Node next;
		Item item;

	}

	private class QueueIterator implements Iterator<Item> {
		Node current = first;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			Item theItem = current.item;
			current = current.next;
			return theItem;
		}

		@Override
		public void remove() {
		}

	}

	Node first;
	Node last;
	int size;

	@Override
	public Iterator<Item> iterator() {
		return new QueueIterator();
	}

	public void push(Item i) {

		if (size == 0) {
			first = new Node();
			first.item = i;
			last = first;
			size++;
			return;
		}
		Node oldLast = last;
		last = new Node();
		last.item = i;
		oldLast.next = last;

		size++;
	}

	public Item pop() {
		Item theItem = null;
		if (first != null) {
			theItem = first.item;
			first = first.next;
			size--;
		}
		if (size == 0)
			last = first;
		return theItem;
	}

}
