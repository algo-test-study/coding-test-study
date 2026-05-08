import java.util.ArrayDeque;
import java.util.Queue;

class Solution {

    static class Node {
        int x;
        int y;
        int count;

        Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public int solution(int[][] maps) {

        int row = maps.length;
        int col = maps[0].length;

        int[][] dir = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        Queue<Node> q = new ArrayDeque<>();
        boolean[][] check = new boolean[row][col];

        q.add(new Node(0, 0, 1));
        check[0][0] = true;

        while (!q.isEmpty()) {

            Node now = q.poll();

            if (now.x == row - 1 && now.y == col - 1) {
                return now.count;
            }

            for (int[] d : dir) {

                int nextX = now.x + d[0];
                int nextY = now.y + d[1];

                if (nextX < 0 || nextY < 0 || nextX >= row || nextY >= col) {
                    continue;
                }

                if (maps[nextX][nextY] == 0 || check[nextX][nextY]) {
                    continue;
                }

                check[nextX][nextY] = true;
                q.add(new Node(nextX, nextY, now.count + 1));
            }
        }

        return -1;
    }
}
