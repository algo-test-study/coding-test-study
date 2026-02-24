import java.io.*;
import java.util.*;

public class Main {
    static int M, N;
    static int[][] box;
    static Queue<int[]> queue = new LinkedList<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        box = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
          
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
              
                if (box[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        bfs();

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
              
                max = Math.max(max, box[i][j]);
            }
        }

        System.out.println(max - 1);
    }

    static void bfs() {
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (box[nx][ny] == 0) {
                        box[nx][ny] = box[x][y] + 1;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}
