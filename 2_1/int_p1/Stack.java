import java.util.Iterator;

class Stack<Item> implements Iterable<Item> {
	private Node first;
	private int size;

	public static void main(String args[]) {
		Stack<String> aStack = new Stack<>();
		aStack.push("hah");
		aStack.push("wala");
		aStack.push("eeee");

		for (String a : aStack) {
			System.out.println(a);
		}
	       
	}

	public Stack() {
		first = null;
		size = 0;
	}

	public Iterator<Item> iterator() {
		return new StackIterator();
	}

	public void push(Item i) {
		Node oldFirst = first;
		first = new Node();
		first.item = i;
		first.next = oldFirst;
		size++;
	}

	public Item pop() {
		Item theItem = null;
		if (first != null) {
			theItem = first.item;
			first = first.next;
			size--;
		}
		return theItem;

	}

	private class StackIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public Item next() {
			Item theItem = current.item;
			current = current.next;
			return theItem;
		}

		public void remove() {

		}
	}

	private class Node {
		Node next;
		Item item;
	}

}
