import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int size;
	private Node<Item> first;
	
	private class Node<Item>{
		Item item;
		Node<Item> next;
	}

	
	public RandomizedQueue() {
		first = new Node<Item>();
		first.next = null;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void enqueue(Item item) {
		if(item == null)
			throw new NullPointerException();
		Node<Item> node = new Node<Item>();
		node.item = item;
		node.next = first.next;
		first.next = node;
		size ++;
	}

	public Item dequeue() {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		int n = StdRandom.uniform(size);
		Node<Item> node = first;
		while(n-->0){
			node = node.next;
		}
		
		Item item = node.next.item;
		node.next = node.next.next;
		
		size --;
		return item;
		
	}

	public Item sample() {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		int n = StdRandom.uniform(size);
		Node<Item> node = first.next;
		while(n-->0){
			node = node.next;
		}

		return node.item;
	}

	@Override
	public Iterator<Item> iterator() {
		return new RandomizedQueueIter(this);
	}
	
	private class RandomizedQueueIter implements Iterator<Item>{
		private int[] order;
		private int orderPos;
		
		public RandomizedQueueIter(RandomizedQueue<Item> queue){
			order = new int[queue.size];
			for(int i=1;i<=queue.size;i++){
				order[i-1]=i;
			}
			StdRandom.shuffle(order);
			orderPos = 0;
		}
		@Override
		public boolean hasNext() {
			return orderPos < size;
		}

		@Override
		public Item next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			int n = order[orderPos++];
			Node<Item> node = first;
			while(n-->0){
				node = node.next;
			}
			return node.item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	} 
}
