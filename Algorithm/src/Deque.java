import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node<Item> first;
	private Node<Item> last;
	private int size = 0;

	private class Node<Item> {
		Item item;
		Node<Item> next;
		Node<Item> prev;
	}

	public Deque() {
		first = new Node<Item>();
		last = new Node<Item>();
		
		first.next = last;
		first.prev = null;

		
		last.next = null;
		last.prev = first;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}
		Node<Item> node = new Node<Item>();
		node.item = item;

		node.next = first.next;
		node.prev = first;
		first.next = node;
		node.next.prev = node;
		size ++;

	}

	public void addLast(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}
		Node<Item> node = new Node<Item>();
		node.item = item;

		node.next = last;
		node.prev = last.prev;
		last.prev.next = node;
		last.prev = node;
		size ++;

	}

	public Item removeFirst() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		Node<Item> node = first.next;
		first.next = first.next.next;
		first.next.prev = first;
		size --;

		return node.item;
	}

	public Item removeLast() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		Node<Item> node = last.prev;
		last.prev = last.prev.prev;
		last.prev.next = last;

		size--;
		return node.item;

	}

	public Iterator<Item> iterator() {
		return new DequeIterator(this);
	}

	private class DequeIterator implements Iterator<Item> {
		private int index;
		private Node<Item> currNode;

		public DequeIterator(Deque deque) {
			index = 0;
			currNode = first;

		}

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();

			}
			currNode = currNode.next;
			index++;
			return currNode.item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
