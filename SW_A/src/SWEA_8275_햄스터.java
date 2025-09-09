


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_8275_햄스터 {
	static int[] cage;
	static int[][] con;
	static int N, X, M, max;
	static int[] res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			cage = new int[N + 1];
			con = new int[M][3];
			res = new int[N+1];
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				con[i][0] = Integer.parseInt(st.nextToken());
				con[i][1] = Integer.parseInt(st.nextToken());
				con[i][2] = Integer.parseInt(st.nextToken());
			}
			
			max = -1;
			dfs(1);
			sb.append("#" + tc + " ");
			if (max == -1) {
				sb.append(max + "\n");
			} else {
				for (int i = 1; i <= N; i++) {
					sb.append(res[i] + " ");					
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

	// dfs + 백트래킹
	public static void dfs(int idx) {
		if (idx > N) {
			if (check()) {
//				System.out.println(check());
				int sum = 0;
				for (int i = 1; i <= N; i++) {
					sum += cage[i];
				}
				if (max < sum) {
					max = sum;
					for (int i = 1; i <= N; i++) {
						res[i] = cage[i];
					}
				}
			}
			return;
		}

		for (int i = 0; i <= X; i++) {
			cage[idx] = i;
			dfs(idx + 1);
		}
	}
	static boolean check() {
		for (int i = 0; i < M; i++) {
			int l = con[i][0];
			int r = con[i][1];
			int s = con[i][2];
			int sum = 0;
			for (int j = l; j <= r; j++) {
				sum += cage[j];
			}
			if(sum != s) return false;
		}
		return true;
	}
}
