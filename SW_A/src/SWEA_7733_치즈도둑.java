import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7733_치즈도둑 {
// 덩어리 나누거나 사이클 체크할 때 DISJOINT-SET을 사용함
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] visited;
	static int[][] cheese;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			cheese = new int[N][N];
			int max_day = 0;
			int min_day = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cheese[i][j] = Integer.parseInt(st.nextToken());
					max_day = Math.max(cheese[i][j], max_day);
					min_day = Math.min(cheese[i][j], min_day);
				}
			}

			int max_cnt = 1;
			for (int day = min_day; day <= max_day; day++) {
				visited = new boolean[N][N];
				int cnt = 0;

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (cheese[i][j] > day && !visited[i][j]) {
							bfs(i, j, day);
							cnt++;
						}
					}
				}
				max_cnt = Math.max(max_cnt, cnt);
			}
			sb.append("#" + tc + " " + max_cnt + "\n");
		}
		System.out.println(sb);
	}

	public static void bfs(int r, int c, int day) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { r, c });
		visited[r][c] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (nr >= 0 && nc >= 0 && nr < N && nc < N && !visited[nr][nc] && cheese[nr][nc] > day) {
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
	}
}
