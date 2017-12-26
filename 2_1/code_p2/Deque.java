
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	private int size;
	private Node head;
	private Node tail;

	// public static void main(String[] args) {

	// }

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void addFirst(Item item) {

		if (item == null)
			throw new java.lang.IllegalArgumentException();
		// Make sure when the queue is empty, the last and the first element are the
		// same.
		if (isEmpty()) {
			head = new Node(item);
			tail = head;

		} else {

			Node oldHead = head;
			head = new Node(item);
			head.next = oldHead;
			oldHead.prev = head;
		}
		size++;

	}

	public void addLast(Item item) {

		if (item == null)
			throw new java.lang.IllegalArgumentException();
		if (isEmpty()) {
			tail = new Node(item);
			head = tail;

		} else {
			tail.next = new Node(item);
			tail.next.prev = tail;
			tail = tail.next;

		}
		size++;
	}

	public Item removeFirst() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		Item i = head.item;
		head = head.next;
		
		if(head == null) return i; // important check
		
		head.prev = null;

		size--;
		return i;
	}

	public Item removeLast() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		Item i = tail.item;
		tail = tail.prev;
		
		if(tail == null) return i; // important check
		
		tail.next = null;

		size--;
		return i;
	}

	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class Node {
		Node next;
		Node prev;
		Item item;

		private Node(Item i) {
			item = i;
		}
	}

	private class DequeIterator implements Iterator<Item> {
		Node current = head;

		public boolean hasNext() {

			return current != null;
		}

		public void remove() {
			throw new java.util.NoSuchElementException();
		}

		public Item next() {

			Item i = current.item;
			current.next = current;

			return i;
		}

	}
}
