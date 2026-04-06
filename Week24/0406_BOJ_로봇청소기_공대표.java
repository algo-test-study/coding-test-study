import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        int count = 0;

        while (true) {
            if (!visited[r][c]) {
                visited[r][c] = true;
                count++;
            }

            boolean moved = false;

            for (int i = 0; i < 4; i++) {
                d = (d + 3) % 4;
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (map[nr][nc] == 0 && !visited[nr][nc]) {
                    r = nr;
                    c = nc;
                    moved = true;
                    break;
                }
            }

            if (!moved) {
                int back = (d + 2) % 4;
                int nr = r + dr[back];
                int nc = c + dc[back];

                if (map[nr][nc] == 1) break;

                r = nr;
                c = nc;
            }
        }

        System.out.print(count);
    }
}
