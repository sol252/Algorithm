


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1952_수영장 {
	static int[] month, price;
	static int min;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= T; tc++) {
			price = new int[4];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			
			month = new int[12];
			st = new StringTokenizer(br.readLine());
			min = price[3];
			
			for (int i = 0; i < 12; i++) {
				month[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, 0, 0, 0);
			sb.append("#" + tc + " " + min + "\n");
		}
		System.out.println(sb);
	}
	
	public static void dfs(int idx, int day, int month1, int month3) {
		if (idx >= 12) {
			int sum = day + month1 + month3;
			min = Math.min(min, sum);
			return;
		}
		if (month[idx] == 0) dfs(idx+1, day, month1, month3);
		if (month[idx] != 0) {			
			dfs(idx + 1, day + month[idx]*price[0], month1, month3);
			dfs(idx + 1, day, month1 + price[1], month3);
			dfs(idx + 3, day, month1, month3 + price[2]);
		}
	}
}
