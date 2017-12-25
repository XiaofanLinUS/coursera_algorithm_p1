import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DequeTest {

	@Test
	void testIsEmpty() {
		Deque<Integer> d = new Deque<Integer>();
		assertTrue(d.isEmpty());
	}

	@Test
	void testSize() {
		Deque<Integer> d = new Deque<Integer>();
		for(int i = 0; i < 10; i++) {
			d.addFirst(i);
		}
		assertEquals(10, d.size());
	}

	@Test
	void testAddFirst() {
		Deque<Integer> d = new Deque<Integer>();
		int[] result = new int[10];
		int[] expect = new int[10];
		for(int i = 0; i < 10; i++) {
			d.addFirst(i);
			expect[i] = 9 - i;
		}
		
		for(int i = 0; i < 10; i++) {
			result[i] = d.removeFirst();

		}
		assertArrayEquals(expect, result);
	}

	@Test
	void testAddLast() {
		Deque<Integer> d = new Deque<Integer>();
		int[] result = new int[10];
		int[] expect = new int[10];
		for(int i = 0; i < 10; i++) {
			d.addLast(i);
			expect[i] = 9 - i;
		}
		
		for(int i = 0; i < 10; i++) {
			result[i] = d.removeLast();
		}
		assertArrayEquals(expect, result);
	}

	@Test
	void testRemoveFirst() {
		Deque<Integer> d = new Deque<Integer>();
		int[] result = new int[10];
		int[] expect = new int[10];
		for(int i = 0; i < 10; i++) {
			d.addLast(i);
			expect[i] = i;
		}
		
		for(int i = 0; i < 10; i++) {
			result[i] = d.removeFirst();
		}
		assertArrayEquals(expect, result);
	}

	@Test
	void testRemoveLast() {
		Deque<Integer> d = new Deque<Integer>();
		int[] result = new int[10];
		int[] expect = new int[10];
		for(int i = 0; i < 10; i++) {
			d.addFirst(i);
			expect[i] = i;
		}
		
		for(int i = 0; i < 10; i++) {
			result[i] = d.removeLast();

		}
		assertArrayEquals(expect, result);
	}

}
