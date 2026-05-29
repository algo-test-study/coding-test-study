import java.util.*;

class Solution {
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, -1, 1};
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    q.offer(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int y = current[0];
            int x = current[1];
            
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                if (ny < 0 || ny >= m || nx < 0 || nx >= n) continue;
                if (visited[ny][nx]) continue;

                mat[ny][nx] = mat[y][x] + 1;
                q.offer(new int[]{ny,nx});
                visited[ny][nx] = true;
            }
        }

        return mat;
    }
}
