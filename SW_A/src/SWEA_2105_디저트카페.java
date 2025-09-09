


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2105_디저트카페 {
	static int[] dr = { -1, 1, 1, -1 };
	static int[] dc = { 1, 1, -1, -1 };
	static int[][] arr;
	static int N;
	static boolean[] visited;
	static int sr, sc, max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			max = -1;
			for (sr = 0; sr < N; sr++) {
				for (sc = 0; sc < N; sc++) {
					visited = new boolean[101];
					dfs(sr, sc, 0, 0);
				}
			}
			sb.append("#" + tc + " " + max + "\n");
		}
		System.out.println(sb);
	}

	public static void dfs(int i, int j, int direct, int cnt) {
		if (i < 0 || j < 0 || i >= N || j >= N || direct > 3) return;
		if (cnt >= 4 && sr == i && sc == j) {
			max = Math.max(max, cnt);
			return;
		}
		if (visited[arr[i][j]]) return;

		visited[arr[i][j]] = true;
		dfs(i + dr[direct], j + dc[direct], direct, cnt + 1);
		dfs(i + dr[direct], j + dc[direct], direct + 1, cnt + 1);
		visited[arr[i][j]] = false;
	}
}
