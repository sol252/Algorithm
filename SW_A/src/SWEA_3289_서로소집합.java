import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3289_서로소집합 {
	static int[] arr, root;

	// 1 2 3 4 5 6 7
	// 1 2 1 4 5 1 1
	// 맨 앞이 0이면 mst로 합치기, 앞이 1이면 root가 같은지 확인하기 find 함수로
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			arr = new int[n + 1];
			root = new int[n + 1];
			for (int i = 0; i < n + 1; i++) {
				root[i] = i;
			}

			sb.append("#").append(tc).append(" ");
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (c == 1) {
					if (check(a, b)) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				} else {
					mst(a, b);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static void mst(int x, int y) {
		int root1 = find(x);
		int root2 = find(y);

		if (root1 < root2) {
			root[root2] = root1;
		} else {
			root[root1] = root2;
		}
	}

	public static boolean check(int x, int y) {
		int root1 = find(x);
		int root2 = find(y);
		if (root1 == root2) {
			return true;
		}
		return false;
	}

	public static int find(int i) {
		if (root[i] == i)
			return i;
		return root[i] = find(root[i]);
	}
}
