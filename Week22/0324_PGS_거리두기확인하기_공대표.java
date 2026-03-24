import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int i = 0; i < 5; i++) {
            answer[i] = check(places[i]) ? 1 : 0;
        }

        return answer;
    }

    private boolean check(String[] place) {
        char[][] map = new char[5][5];

        for (int i = 0; i < 5; i++) {
            map[i] = place[i].toCharArray();
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] != 'P') continue;

                Queue<int[]> q = new LinkedList<>();
                boolean[][] visited = new boolean[5][5];

                q.offer(new int[]{i, j, 0});
                visited[i][j] = true;

                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int x = cur[0];
                    int y = cur[1];
                    int dist = cur[2];

                    if (dist >= 2) continue;

                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
                        if (visited[nx][ny]) continue;
                        if (map[nx][ny] == 'X') continue;

                        if (map[nx][ny] == 'P') return false;

                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny, dist + 1});
                    }
                }
            }
        }

        return true;
    }
}
