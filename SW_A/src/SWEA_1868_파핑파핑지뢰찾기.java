import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class SWEA_1868_파핑파핑지뢰찾기 {
	static int N;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {-1,-1,-1,0,0,1,1,1};
    static int[] dc = {-1,0,1,-1,1,-1,0,1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            visited = new boolean[N][N];

            for(int i=0; i<N; i++) {
                map[i] = br.readLine().toCharArray();
                
            }

            // 숫자 변환
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j] == '.') {
                        int mine_cnt = 0;
                        for(int d=0; d<8; d++) {
                            int nr = i + dr[d];
                            int nc = j + dc[d];
                            if(nr>=0 && nc>=0 && nr<N && nc<N && map[nr][nc] == '*') 
                            	mine_cnt++;
                        }
                        map[i][j] = (char)(mine_cnt + '0');
                    }
                }
            }

            int cnt = 0;
            // 0 영역부터 BFS로 열기
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j] == '0' && !visited[i][j]) {
                        bfs(i,j);
                        cnt++;
                    }
                }
            }

            // 남은 숫자칸 클릭
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j] != '*' && !visited[i][j]) {
                        visited[i][j] = true;
                        cnt++;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(cnt).append("\n");
        }
        System.out.print(sb);
    }

    static void bfs(int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        visited[r][c] = true;
        q.offer(new int[] {r,c});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0], cc = cur[1];
            
            if(map[cr][cc] == '0') { // 주변이 0인 경우만 확장
                for(int d=0; d<8; d++) {
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];
                    if(nr>=0 && nc>=0 && nr<N && nc<N && !visited[nr][nc] && map[nr][nc] != '*') {
                        visited[nr][nc] = true;
                        q.offer(new int[] {nr, nc});
                    }
                }
            }
        }
    }
}