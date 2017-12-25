public class Deque<Item> implements Iterable<Item> {
	private int size;
	private Node head;
	private Node tail;

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void addFirst(Item item) {

		if (isEmpty()) {
			head = new Node(item);
			tail = head;

		} else {

			Node oldHead = head;
			head = new Node(item);
			head.next = oldHead;
		}
		size++;

	}

	public void addLast(Item item) {
		if (isEmpty()) {
			tail = new Node(item);
			head = tail;

		} else {
			tail.next = new Node(item);
			tail = tail.next;
		}
		size++;
	}

	public Item removeFirst() {
		return null;
	}

	public Item removeLast() {
		return null;
	}

	public Iterator<Item> iterator() {
		return null;
	}

	private class Node {
		Node next;
	        Node prev;
		Item item;

		private Node(Item i) {
			item = i;
		}
	}
}
