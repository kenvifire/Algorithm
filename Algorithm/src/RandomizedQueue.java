import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int size;
	private Item[] items;
	private int head = 0;

	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		items = (Item[]) new Object[1];
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void enqueue(Item item) {
		if (item == null)
			throw new NullPointerException();
		size++;
		if (head == items.length)
			resize(size * 2);
		items[head++] = item;
	}

	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		if (size > 0 && size == items.length / 4)
			resize(items.length / 2);
		// random select
		int n = StdRandom.uniform(items.length);
		while (items[n] == null) {
			n = StdRandom.uniform(items.length);
		}
		Item item = items[n];
		items[n] = null;
		size--;
		return item;

	}

	public Item sample() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		// random select
		int n = StdRandom.uniform(items.length);
		while (items[n] == null) {
			n = StdRandom.uniform(items.length);
		}

		return items[n];
	}

	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		int i = 0;
		head = 0;
		for (Item item : items) {
			if (item != null) {
				copy[i++] = item;
				head++;
			}
		}

		items = copy;

	}

	@Override
	public Iterator<Item> iterator() {
		return new RandomizedQueueIter(this);
	}

	private class RandomizedQueueIter implements Iterator<Item> {
		private Item[] orderItems;
		private int orderPos;

		@SuppressWarnings("unchecked")
		public RandomizedQueueIter(RandomizedQueue<Item> queue) {
			orderItems = (Item[]) new Object[queue.size];
			if (queue.size == 0) {
				orderPos = 0;
				return;
			}

			int i = 0;
			for (Item item : items) {
				if (item != null)
					orderItems[i++] = item;
			}
			StdRandom.shuffle(orderItems);
			orderPos = 0;
		}

		@Override
		public boolean hasNext() {
			return orderPos < orderItems.length;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return orderItems[orderPos++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
}
