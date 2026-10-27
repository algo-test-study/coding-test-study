import java.util.*;

class Solution {

    public int[] solution(String[] maps) {
        int rows = maps.length;
        int cols = maps[0].length();

        boolean[][] visited = new boolean[rows][cols];

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        List<Integer> results = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                if (maps[r].charAt(c) == 'X' || visited[r][c]) {
                    continue;
                }
                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[]{r, c});
                visited[r][c] = true;
                int days = 0;
                while (!queue.isEmpty()) {
                    int[] current = queue.poll();

                    int row = current[0];
                    int col = current[1];

                    days += maps[row].charAt(col) - '0';

                    for (int d = 0; d < 4; d++) {
                        int nr = row + dr[d];
                        int nc = col + dc[d];

                        if (nr < 0 || nr >= rows ||
                            nc < 0 || nc >= cols) {
                            continue;
                        }
                        
                        if (maps[nr].charAt(nc) == 'X' ||
                            visited[nr][nc]) {
                            continue;
                        }
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                    }
                }
                results.add(days);
            }
        }

        if (results.isEmpty()) {
            return new int[]{-1};
        }
        Collections.sort(results);

        return results.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
