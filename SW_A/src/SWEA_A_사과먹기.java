import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_A_사과먹기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			int[][] apple = new int[N * N + 1][3];
			int sr = 0;
			int sc = 0;
			int cur_direct = 0;
			int max = 0;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] != 0) {
						apple[map[i][j]][0] = i;
						apple[map[i][j]][1] = j;
						apple[map[i][j]][2] = map[i][j];
						max = Math.max(max, map[i][j]);
					}
				}
			}

			int cnt = 0;
			for (int i = 1; i <= max; i++) {
				int nr = apple[i][0] - sr;
				int nc = apple[i][1] - sc;
				if (cur_direct == 0) {
					if (nr > 0 && nc > 0) {
						cnt += 1;
						cur_direct = (cur_direct + 1) % 4;
					} else if (nr > 0 && nc < 0) {
						cnt += 2;
						cur_direct = (cur_direct + 2) % 4;
					} else {
						cnt += 3;
						cur_direct = (cur_direct + 3) % 4;
					}
				} else if (cur_direct == 1) {
					if (nr > 0 && nc < 0) {
						cnt += 1;
						cur_direct = (cur_direct + 1) % 4;
					} else if (nr < 0 && nc < 0) {
						cnt += 2;
						cur_direct = (cur_direct + 2) % 4;
					} else {
						cnt += 3;
						cur_direct = 0;
					}
				} else if (cur_direct == 2) {
					if (nr < 0 && nc < 0) {
						cnt += 1;
						cur_direct = (cur_direct + 1) % 4;
					} else if (nr < 0 && nc > 0) {
						cnt += 2;
						cur_direct = 0;
					} else {
						cnt += 3;
						cur_direct = 1;
					}
				} else if (cur_direct == 3) {
					if (nr < 0 && nc > 0) {
						cnt += 1;
						cur_direct = 0;
					} else if (nr > 0 && nc > 0) {
						cnt += 2;
						cur_direct = 1;
					} else {
						cnt += 3;
						cur_direct = 2;
					}
				}
				sr = apple[i][0];
				sc = apple[i][1];
			}
			System.out.println("#" + tc + " " + cnt);
		}
	}
}
