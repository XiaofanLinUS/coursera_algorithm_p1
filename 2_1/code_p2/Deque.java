
import java.util.Iterator;

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
		Item i = head.item;
		head = head.next;
		head.prev = null;

		size--;
		return i;
	}

	public Item removeLast() {
		Item i = tail.item;
		tail = tail.prev;
		tail.next = null;

		size--;
		return i;
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

	private class DequeIterator<Item> implements Iterator<Item> {
		Node current = head;

		public boolean hasNext() {

			return current != null;
		}

		public Item next() {
			Item i = current.item;
			current.next = current;
			return i;
		}

	}
}
