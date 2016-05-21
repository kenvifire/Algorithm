import edu.princeton.cs.algs4.Stopwatch;
import junit.framework.TestCase;

import org.junit.Test;

public class RandomizedQueueTest extends TestCase {
	@Test
	public void testEnqueue() {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		assertEquals(true, rq.isEmpty());
		assertEquals(0, rq.size());

		rq.enqueue(100);
		assertEquals(false, rq.isEmpty());
		assertEquals(1, rq.size());
	}

	@Test
	public void testDequeue() {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		rq.enqueue(100);
		rq.dequeue();

		assertEquals(true, rq.isEmpty());
		assertEquals(0, rq.size());
	}

	@Test
	public void testIterator() {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		rq.enqueue(100);
		rq.enqueue(200);
		rq.enqueue(300);
		assertEquals(3, rq.size());

		for (Integer i : rq) {
			System.out.println(i);
		}

	}

	@Test
	public void testSample() {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		rq.enqueue(100);
		// rq.enqueue(200);
		// rq.enqueue(300);
		assertEquals(100, rq.sample().intValue());

		// for(int i=0;i<100;i++){
		// System.out.println(rq.sample());
		// }
	}

	@Test
	public void testSampleFreq() {
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		rq.enqueue("A");
		rq.enqueue("B");
		rq.enqueue("C");
		int countA = 0, countB = 0, countC = 0;
		for (int i = 0; i < 1500; i++) {
			String sample = rq.sample();
			if ("A".equals(sample))
				countA++;
			if ("B".equals(sample))
				countB++;
			if ("C".equals(sample))
				countC++;
		}
		System.out.println("A:" + countA + ",B:" + countB + ",C:" + countC);
	}

	@Test
	public void testDequeFreq() {
		int count = 0;
		for (int n = 0; n < 3; n++) {
			int count1 = 0, count2 = 0, count3 = 0;
			for (int i = 0; i < 1500; i++) {
				RandomizedQueue<String> rq = new RandomizedQueue<String>();
				rq.enqueue("A");
				rq.enqueue("B");
				rq.enqueue("C");
				if ("A".equals(rq.dequeue())) {
					count1++;
				}
				if ("A".equals(rq.dequeue())) {
					count2++;
				}
				if ("A".equals(rq.dequeue())) {
					count3++;
				}
			}
			System.out.println("1:" + count1 + ",2:" + count2 + ",3:" + count3);
		}

	}

	@Test
	public void testTime() {
		int n = 16384;
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		Stopwatch watch = new Stopwatch();
		for (int i = 0; i < n; i++) {
			System.out.println(i);
			// System.out.println("enqueing");
			rq.enqueue("A");
			// System.out.println("sampling");
			rq.sample();
			// System.out.println("dequeuing");
			rq.dequeue();
			// System.out.println("is empty");
			rq.isEmpty();
			// System.out.println("sizing");
			rq.size();
		}
		System.out.println(watch.elapsedTime());

	}

}
