
public class LinkedStackOfStrings {
	private Node first;
	
	private class Node{
		String item;
		Node next;
	}
	
	private Node pop(){
		String item = first.item;
		first = first.next;
		return first;
	}

}
