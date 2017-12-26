import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] items;
	private int size;
	private final int ScaleFactor = 2;

	public Iterator<Item> iterator() {

		return new RQIterator();
	}

	public Item sample() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();

		return items[StdRandom.uniform(size)];

	}

	public RandomizedQueue() {
		items = (Item[]) new Object[1];
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void enqueue(Item item) {
		if (item == null)
			throw new java.lang.IllegalArgumentException();

		size++;
		if (size == items.length)
			enlarge();

		int i = size - 1;
		items[i] = item;
		swap(StdRandom.uniform(size), i);
	}

	public Item dequeue() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();

		Item i = items[--size];
		items[size] = null;
		if (size <= items.length / (ScaleFactor * ScaleFactor))
			shrink();
		return i;
	}

	private void enlarge() {
		Object[] newPlace = new Object[items.length * ScaleFactor];
		for (int i = 0; i < size; i++) {
			newPlace[i] = items[i];
		}
		items = (Item[]) newPlace;

	}

	private void shrink() {
		Object[] newPlace = new Object[items.length / ScaleFactor];

		for (int i = 0; i < size; i++) {
			newPlace[i] = items[i];
		}
		items = (Item[]) newPlace;
	}

	private void swap(int i, int j) {
		Item tmp = items[i];
		items[i] = items[j];
		items[j] = tmp;
	}

	public static void main(String[] args) {
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		for (int i = 0; i <= 9; i++) {
			rq.enqueue(i);
		}
		for (int e : rq) {
			System.out.println(e);
		}
		System.out.println("New");
		for (int e : rq) {
			System.out.println(e);
		}
		
		System.out.println("New");
		for (int e : rq) {
			System.out.println(e);
		}

		
		System.out.println("New");
		for (int e : rq) {
			System.out.println(e);
		}
	}

	private class RQIterator implements Iterator<Item> {
		int current;
		int[] randomIndices;

		public RQIterator() {
			randomIndices = new int[size];
			for (int i = 0; i < size; i++) {
				randomIndices[i] = i;
			}
			StdRandom.shuffle(randomIndices);
		}

		public boolean hasNext() {
			return current < size;
		}

		public Item next() {
			return items[randomIndices[current++]];
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}

	}
}
