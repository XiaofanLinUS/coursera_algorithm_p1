import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] items;
	private int size;
	private final int ScaleFactor = 2;

        public Iterator<Item> iterator() {

		return null;
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
		size++;
		if (size == items.length)
			enlarge();

		int i = size - 1;
		items[i] = item;
		swap(StdRandom.uniform(size), i);
	}

	public Item dequeue() {
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
		for (int i = 0; i <= 9; i++) {
			System.out.println(rq.dequeue());
		}

	}
}
