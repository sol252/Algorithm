import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7456_창용마을무리의개수 {
	static ArrayList<Integer>[] arr;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		// 사람수 6, 관계수 5
		// 연결리스트 느낌?
		// 1   2   3   4   5   6
		// 2,5 1,5 4   3,6 2,1 4
		// 큐 사용하면 될듯? bfs로다가
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			arr = new ArrayList[N + 1];
			for (int i = 0; i < N + 1; i++) {
				arr[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				arr[a].add(b);
				arr[b].add(a);
			}
			
			visited = new boolean[N+1];
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					bfs(i);
					cnt++;
				}
			}
			sb.append("#" + tc + " " + cnt + "\n");
		}
		System.out.println(sb);
	}

	public static void bfs(int idx) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(idx);
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i : arr[cur]) {
				if (!visited[i]) {
					q.add(i);
					visited[i] = true;
				}
			}
		}
	}
}
