import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class DequeTest extends junit.framework.TestCase {
	public DequeTest(String name) {
		super(name);
	}

	public static void main(String args[]) {
	    junit.textui.TestRunner.main(new String[] { "DequeTest" });

	}

	@Test
	public void testIsEmpty() {
		Deque<Integer> d = new Deque<Integer>();
		assertTrue(d.isEmpty());
	}

	@Test
	public void testSize() {
		Deque<Integer> d = new Deque<Integer>();
		for (int i = 0; i < 10; i++) {
			d.addFirst(i);
		}
		assertEquals(10, d.size());
	}

	@Test
	public void testAddFirst() {
		Deque<Integer> d = new Deque<Integer>();
		int[] result = new int[10];
		int[] expect = new int[10];
		for (int i = 0; i < 10; i++) {
			d.addFirst(i);
			expect[i] = 9 - i;
		}

		for (int i = 0; i < 10; i++) {
			result[i] = d.removeFirst();

		}
		assertArrayEquals(expect, result);
	}

	@Test
	public void testAddLast() {
		Deque<Integer> d = new Deque<Integer>();
		int[] result = new int[10];
		int[] expect = new int[10];
		for (int i = 0; i < 10; i++) {
			d.addLast(i);
			expect[i] = 9 - i;
		}

		for (int i = 0; i < 10; i++) {
			result[i] = d.removeLast();
		}
		assertArrayEquals(expect, result);
	}

	@Test
	public void testRemoveFirst() {
		Deque<Integer> d = new Deque<Integer>();
		int[] result = new int[10];
		int[] expect = new int[10];
		for (int i = 0; i < 10; i++) {
			d.addLast(i);
			expect[i] = i;
		}

		for (int i = 0; i < 10; i++) {
			result[i] = d.removeFirst();
		}
		assertArrayEquals(expect, result);
	}


        @Test
	public void testIterator() {

		Deque<Integer> d = new Deque<Integer>();
		int[] result = new int[10];
		int[] expect = new int[10];
		for (int i = 0; i < 10; i++) {
			d.addFirst(i);
			expect[i] = 9 - i;
		}

		Iterator<Integer> dequeIterator = d.iterator();
		int i = 0;
		while(dequeIterator.hasNext()) {
		    result[i++] = dequeIterator.next();

		}
		assertArrayEquals(expect, result);

	}
        @Test
	public void testRemoveLast() {
		Deque<Integer> d = new Deque<Integer>();
		int[] result = new int[10];
		int[] expect = new int[10];
		for (int i = 0; i < 10; i++) {
			d.addFirst(i);
			expect[i] = i;
		}

		for (int i = 0; i < 10; i++) {
			result[i] = d.removeLast();

		}
		assertArrayEquals(expect, result);
	}

}
