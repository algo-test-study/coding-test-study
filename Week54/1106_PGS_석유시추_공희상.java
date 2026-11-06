import java.util.*;

class Solution {

    public int solution(int[][] land) {
        int rows = land.length;
        int cols = land[0].length;

        boolean[][] visited = new boolean[rows][cols];
        int[] oilByColumn = new int[cols];

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                if (land[r][c] == 0 || visited[r][c]) {
                    continue;
                }

                Queue<int[]> queue = new ArrayDeque<>();

                queue.offer(new int[]{r, c});
                visited[r][c] = true;

                int size = 0;
                int minCol = c;
                int maxCol = c;

                while (!queue.isEmpty()) {
                    int[] current = queue.poll();

                    int row = current[0];
                    int col = current[1];

                    size++;

                    minCol = Math.min(minCol, col);
                    maxCol = Math.max(maxCol, col);

                    for (int d = 0; d < 4; d++) {
                        int nr = row + dr[d];
                        int nc = col + dc[d];

                        if (nr < 0 || nr >= rows ||
                            nc < 0 || nc >= cols) {
                            continue;
                        }

                        if (land[nr][nc] == 0 || visited[nr][nc]) {
                            continue;
                        }

                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                    }
                }

                for (int col = minCol; col <= maxCol; col++) {
                    oilByColumn[col] += size;
                }
            }
        }

        int answer = 0;

        for (int oil : oilByColumn) {
            answer = Math.max(answer, oil);
        }

        return answer;
    }
}
