class Solution {
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int maxY = image.length;
        int maxX = image[0].length;

        int number = image[sr][sc];
        if (number == color) {
            return image;
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[maxY][maxX];
        q.offer(new int[]{sr,sc});
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int currentY = current[0];
            int currentX = current[1];

            image[currentY][currentX] = color;
            
            for (int i = 0; i < 4; i++) {
                int nextY = currentY + dy[i];
                int nextX = currentX + dx[i];

                if (nextY < maxY && nextY >= 0
                && nextX < maxX && nextX >= 0) {
                    if (!visited[nextY][nextX] 
                    && image[nextY][nextX] == number) {
                        q.offer(new int[]{nextY, nextX});
                        visited[nextY][nextX] = true;
                    }
                }
            }
        }
        return image;
    }
}
