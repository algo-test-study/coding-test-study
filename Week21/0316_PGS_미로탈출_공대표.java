import java.util.*;

class Solution {

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public int solution(String[] maps) {
        int n = maps.length;
        int m = maps[0].length();

        int sx = 0, sy = 0;
        int lx = 0, ly = 0;
        int ex = 0, ey = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                char c = maps[i].charAt(j);

                if (c == 'S') {
                    sx = i;
                    sy = j;
                } else if (c == 'L') {
                    lx = i;
                    ly = j;
                } else if (c == 'E') {
                    ex = i;
                    ey = j;
                }
            }
        }

        int toLever = bfs(maps, sx, sy, lx, ly);
        if (toLever == -1) return -1;

        int toExit = bfs(maps, lx, ly, ex, ey);
        if (toExit == -1) return -1;

        return toLever + toExit;
    }

    public int bfs(String[] maps, int sx, int sy, int tx, int ty) {
        int n = maps.length;
        int m = maps[0].length();

        boolean[][] visited = new boolean[n][m];

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy, 0});

        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];

            if (x == tx && y == ty) {
                return dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visited[nx][ny]) continue;
                if (maps[nx].charAt(ny) == 'X') continue;

                visited[nx][ny] = true;

                q.add(new int[]{nx, ny, dist + 1});
            }
        }

        return -1;
    }
}
