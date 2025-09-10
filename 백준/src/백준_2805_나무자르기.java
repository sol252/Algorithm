import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_2805_나무자르기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		int max = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}

		int start = 0;
		int end = max;
		int tmp = 0;

		while (start <= end) {
			int mid = (start + end) / 2;
			long sum = 0;

			for (int h : arr) {
				if (h > mid) {
					sum += h - mid;
				}
			}

			if (sum >= M) {
				tmp = mid;
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		System.out.println(tmp);
	}
}
