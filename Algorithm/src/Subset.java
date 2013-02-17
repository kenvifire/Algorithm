
public class Subset {
	public static void main(String[] args){
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		String str = null;
		while (!StdIn.isEmpty()) {
          str = StdIn.readString();
          rq.enqueue(str);
        }
		while(k-->0){
			StdOut.println(rq.dequeue());
		}
	}
}
