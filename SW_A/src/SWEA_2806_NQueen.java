


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_2806_NQueen {
	static int N, ans;
	static boolean[] col, diag1, diag2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			ans = 0;

			col = new boolean[N];
			diag1 = new boolean[2 * N];
			diag2 = new boolean[2 * N];

			dfs(0);

			System.out.println("#" + tc + " " + ans);
		}
	}

	static void dfs(int row) {
		if (row == N) { // N개의 퀸을 모두 배치했다면
			ans++;
			return;
		}

		for (int c = 0; c < N; c++) {
			if (col[c] || diag1[row - c + N] || diag2[row + c])
				continue;

			// 배치
			col[c] = diag1[row - c + N] = diag2[row + c] = true;

			dfs(row + 1);

			// 되돌리기 (백트래킹)
			col[c] = diag1[row - c + N] = diag2[row + c] = false;
		}
	}
}
