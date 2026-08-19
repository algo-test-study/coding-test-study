import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map = new int[102][102];

        for (int[] coordinates : rectangle) {
            int x1 = coordinates[0] * 2;
            int y1 = coordinates[1] * 2;
            int x2 = coordinates[2] * 2;
            int y2 = coordinates[3] * 2;

            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    map[x][y] = 1;
                }
            }
        }

        for (int[] coordinates : rectangle) {
            int x1 = coordinates[0] * 2;
            int y1 = coordinates[1] * 2;
            int x2 = coordinates[2] * 2;
            int y2 = coordinates[3] * 2;

            for (int x = x1 + 1; x < x2; x++) {
                for (int y = y1 + 1; y < y2; y++) {
                    map[x][y] = 2;
                }
            }
        }

        int[][] distance = new int[102][102];
        for (int[] row : distance) {
            Arrays.fill(row, -1);
        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        Queue<int[]> queue = new ArrayDeque<>();
        int startX = characterX * 2;
        int startY = characterY * 2;
        int endX = itemX * 2;
        int endY = itemY * 2;

        queue.offer(new int[]{startX, startY});
        distance[startX][startY] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (current[0] == endX && current[1] == endY) {
                return distance[current[0]][current[1]] / 2;
            }

            for (int direction = 0; direction < 4; direction++) {
                int nextX = current[0] + dx[direction];
                int nextY = current[1] + dy[direction];

                if (map[nextX][nextY] == 1 && distance[nextX][nextY] == -1) {
                    distance[nextX][nextY] = distance[current[0]][current[1]] + 1;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }

        return -1;
    }
}
