public class UnionSet {
	int[] items;
	int[] ranks;

	public static void main(String[] args) {
		UnionSet test = new UnionSet(10);

		test.union(8, 2);
		test.union(8, 3);

		test.union(4, 5);
		test.union(5, 1);

		test.union(6, 9);
		test.union(9, 0);
		test.union(7, 0);

		for (int i = 0; i <= 9; i++) {
			for (int j = i + 1; j <= 9; j++) {
				System.out.printf("%d, %d: %b \n", i, j, test.connected(i, j));
			}
		}

	}
	
	public UnionSet(int x) {
		items = new int[x];
		ranks = new int[x];
		counts = 0;
		
		for (int i = 0; i < x; i++) {
			ranks[i] = 1;
			items[i] = i;
		}
	}

	// Remember to compress the tree
	private int find(int e) {
		int id = items[e];
		while (id != items[id]) {
			items[id] = items[items[id]];
			id = items[id];
		}
		return id;
	}

	// Remember to comp the ranks
	public void union(int p, int q) {
		if (p == q)
			return;
		
		int pid = find(p);
		int qid = find(q);

		if (pid == qid)
			return;
		
		if (ranks[pid] > ranks[qid]) {
			items[qid] = pid;
			ranks[pid] += ranks[qid];
		} else {
			items[pid] = qid;
			ranks[qid] += ranks[pid];
		}
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

}

