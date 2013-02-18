import junit.framework.TestCase;

import org.junit.Test;

public class DequeTest extends TestCase {
	@Test
	public void testEmpty() {
		Deque deque = new Deque();
		assertEquals(true, deque.isEmpty());
		assertEquals(0, deque.size());
	}

	@Test
	public void testAddFirst() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(100);
		assertEquals(false, deque.isEmpty());
		assertEquals(1, deque.size());

		deque.addFirst(100);
		assertEquals(2, deque.size());

	}

	@Test
	public void testAddLast() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addLast(100);
		assertEquals(false, deque.isEmpty());
		assertEquals(1, deque.size());

		deque.addLast(100);
		assertEquals(2, deque.size());
	}

	@Test
	public void testRemoveFirst() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(100);
		deque.removeFirst();
		assertEquals(true, deque.isEmpty());
		assertEquals(0, deque.size());
	}

	@Test
	public void testRemoveLast() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addLast(100);
		deque.removeFirst();
		assertEquals(true, deque.isEmpty());
		assertEquals(0, deque.size());
	}

	@Test
	public void testIterator() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(100);
		deque.addFirst(200);
		deque.addFirst(200);
		deque.addFirst(300);
		for (Integer i : deque) {
			System.out.println(i);
		}
	}

}
