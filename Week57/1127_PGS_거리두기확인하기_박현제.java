import java.util.*;
class Solution {
    static final int n = 5;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public int[] solution(String[][] places) {

        int[] answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            answer[i] = isValid(places[i]);
        }

        return answer;
    }

    boolean bfs(int sx, int sy, int d, String[] place) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];

        visited[sx][sy] = true;
        q.offer(new int[]{sx, sy, 0});

        while (!q.isEmpty()) {

            int[] curr = q.poll();

            int cx = curr[0];
            int cy = curr[1];
            int cd = curr[2];

            if (cd >= d) {
                continue;
            }

            for (int i = 0; i < 4; i++) {

                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n && place[nx].charAt(ny) != 'X' &&!visited[nx][ny]) {

                    if (place[nx].charAt(ny) == 'P') {
                        return false;
                    }

                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny, cd + 1});
                }
            }
        }

        return true;
    }

    int isValid(String[] place) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (place[i].charAt(j) == 'P') {
                    if (!bfs(i, j, 2, place)) {
                        return 0;
                    }
                }
            }
        }

        return 1;
    }
}
