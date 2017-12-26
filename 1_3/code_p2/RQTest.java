import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RQTest extends junit.framework.TestCase {

	public RQTest(String name) {
		super(name);
	}

	@Test
	public void testIsEmpty() {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		assertTrue(rq.isEmpty());
	}

	@Test
	public void testSize() {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		for (int i = 0; i < 10; i++) {
			rq.enqueue(i);
		}
		assertEquals(10, rq.size());
	}

	// public void testEnquequeException() {
	// 	RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
	// 	rq.enqueue(null);
	// 	int r = rq.dequeue();
	// 	assertEquals(r, 1);
	// }
}
