import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int answer = -1;
        
        int n = maps.length;       
        int m = maps[0].length;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        boolean[][] visited = new boolean[n][m];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int distance = cur[2];

            if (x == n - 1 && y == m - 1) {
                answer = distance;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (maps[nx][ny] == 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny, distance + 1});
                    }
                }
            }
        }

        return answer;
    }
}
