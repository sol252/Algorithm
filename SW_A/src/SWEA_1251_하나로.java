import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_1251_하나로 {
	public static class Edge implements Comparable<Edge> {
		int a;
		int b;
		long cost;
		
		public Edge(int a, int b, long cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Long.compare(this.cost, o.cost);
		}
	}
	public static int[] p;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] loc = new int[N][2];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				loc[i][0] = x;
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int y = Integer.parseInt(st.nextToken());
				loc[i][1] = y;
			}
			
			double E = Double.parseDouble(br.readLine());
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					int dx = loc[i][0] - loc[j][0];
					int dy = loc[i][1] - loc[j][1];
					long cost = (long) dx * dx + dy * dy;
					pq.offer(new Edge(i, j, cost));
				}
			}
			
			makeSet(N);
			long sumCost = 0;
			int select = 0;
			while (pq.size() > 0) {
				Edge edge = pq.poll();
				int a = edge.a;
				int b = edge.b;
				if (union(a, b)) {
					select++;
					sumCost += edge.cost;
					if (select == N-1) {
						break;
					}
				}
			}
			sb.append("#" + tc + " " + Math.round(sumCost*E) + "\n");
		}
		System.out.println(sb);
	}
	
	public static void makeSet(int n) {
		p = new int[n];
		for (int i = 0; i < n; i++) {
			p[i] = i;
		}
	}
	
	public static boolean union (int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if (px == py) {
			return false;
		}
		p[py] = px;
		return true;
	}
	
	public static int findSet(int x) {
		if (p[x] == x) return x;
		return p[x] = findSet(p[x]);
	}
}
