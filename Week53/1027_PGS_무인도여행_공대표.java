import java.util.*;

class Solution {
    
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    boolean[][] visited;
    String[] maps;
    int rows, cols;

    public int[] solution(String[] maps) {
        this.maps = maps;
        rows = maps.length;
        cols = maps[0].length();
        visited = new boolean[rows][cols];

        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maps[i].charAt(j) != 'X' && !visited[i][j]) {
                    answer.add(dfs(i, j));
                }
            }
        }

        if (answer.isEmpty()) {
            return new int[]{-1};
        }

        Collections.sort(answer);

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public int dfs(int x, int y) {
        visited[x][y] = true;
        int sum = maps[x].charAt(y) - '0';

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= rows || ny < 0 || ny >= cols) {
                continue;
            }

            if (maps[nx].charAt(ny) == 'X' || visited[nx][ny]) {
                continue;
            }

            sum += dfs(nx, ny);
        }

        return sum;
    }
}
