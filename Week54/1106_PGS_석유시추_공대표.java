
import java.util.*;

class Solution {
    int n, m;
    int[][] land;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int solution(int[][] land) {
        this.land = land;
        n = land.length;
        m = land[0].length;

        int[] oil = new int[m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1) {
                    Set<Integer> columns = new HashSet<>();

                    int size = dfs(i, j, columns);

                    for (int column : columns) {
                        oil[column] += size;
                    }
                }
            }
        }

        int answer = 0;

        for (int amount : oil) {
            answer = Math.max(answer, amount);
        }

        return answer;
    }

    public int dfs(int x, int y, Set<Integer> columns) {
        land[x][y] = 0;

        int count = 1;
        columns.add(y);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                continue;
            }

            if (land[nx][ny] == 1) {
                count += dfs(nx, ny, columns);
            }
        }

        return count;
    }
}
