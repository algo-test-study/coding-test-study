import java.util.*;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map = new int[102][102];
        
        for (int[] r : rectangle) {
            int x1 = r[0] * 2, y1 = r[1] * 2, x2 = r[2] * 2, y2 = r[3] * 2;
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    if (map[x][y] == 2) continue;
                    
                    if (x == x1 || x == x2 || y == y1 || y == y2) {
                        map[x][y] = 1;
                    } else {
                        map[x][y] = 2;
                    }
                }
            }
        }
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{characterX * 2, characterY * 2});
        
        boolean[][] visited = new boolean[102][102];
        visited[characterX * 2][characterY * 2] = true;
        
        int[][] dist = new int[102][102];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            
            if (cx == itemX * 2 && cy == itemY * 2) {
                return dist[cx][cy] / 2;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if (nx >= 0 && nx <= 100 && ny >= 0 && ny <= 100) {
                    if (map[nx][ny] == 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        dist[nx][ny] = dist[cx][cy] + 1;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        return 0;
    }
}
