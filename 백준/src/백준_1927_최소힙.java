import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 백준_1927_최소힙 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> heap = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		heap.add(0);

		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			if (x > 0) {
				heap.add(x);
				int cur = heap.size() - 1;

				while (cur > 1 && heap.get(cur) < heap.get(cur / 2)) {
					int temp = heap.get(cur);
					heap.set(cur, heap.get(cur / 2));
					heap.set(cur / 2, temp);
					cur = cur / 2;
				}
			} else {
				if (heap.size() == 1) {
					sb.append(0 + "\n");
				} else {
					int min = heap.get(1);
					sb.append(min + "\n");
					
					int last = heap.remove(heap.size() - 1);
					if (heap.size() > 1) {
						heap.set(1, last);
						
						int cur = 1;
						while(true) {
							int left = cur * 2;
							int right = cur * 2 + 1;
							int small = cur;
							if (left < heap.size() && heap.get(left) < heap.get(small)) {
								small = left;
							}
							if (right < heap.size() && heap.get(right) < heap.get(small)) {
								small = right;
							}
							if (small == cur) {
								break;
							}
							
							int temp = heap.get(cur);
							heap.set(cur, heap.get(small));
							heap.set(small, temp);
							
							cur = small;
						}
					}
				}
			}
		}
		System.out.println(sb);
	}
}
