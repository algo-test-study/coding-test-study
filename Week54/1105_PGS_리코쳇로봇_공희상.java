import java.util.*;

class Solution {

    public int solution(String[] board) {
        int rows = board.length;
        int cols = board[0].length();

        int startRow = 0;
        int startCol = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r].charAt(c) == 'R') {
                    startRow = r;
                    startCol = c;
                }
            }
        }

        int[][] distance = new int[rows][cols];

        for (int[] row : distance) {
            Arrays.fill(row, -1);
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{startRow, startCol});
        distance[startRow][startCol] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];

            if (board[row].charAt(col) == 'G') {
                return distance[row][col];
            }

            for (int d = 0; d < 4; d++) {
                int nr = row;
                int nc = col;

                while (true) {
                    int nextRow = nr + dr[d];
                    int nextCol = nc + dc[d];

                    if (nextRow < 0 || nextRow >= rows ||
                        nextCol < 0 || nextCol >= cols) {
                        break;
                    }

                    if (board[nextRow].charAt(nextCol) == 'D') {
                        break;
                    }

                    nr = nextRow;
                    nc = nextCol;
                }

                if (distance[nr][nc] != -1) {
                    continue;
                }

                distance[nr][nc] = distance[row][col] + 1;
                queue.offer(new int[]{nr, nc});
            }
        }

        return -1;
    }
}
