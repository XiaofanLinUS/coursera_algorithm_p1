import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
	public static void main(String[] args) {

		if (args.length > 0) {
			int numb = Integer.parseInt(args[0]);
			RandomizedQueue<String> rq = new RandomizedQueue<>();
			while (!StdIn.isEmpty()) {
				String item = StdIn.readString();
				rq.enqueue(item);
			}
			int i = 0;
			for (String e : rq) {
				StdOut.println(e);
				i++;
				if(i == numb) break;
			}
		}
	}
}
