import java.util.*;
class Solution {
    static Map<Integer, Integer> id;
    static int n, m;
    static int max;
    static boolean [][] visited;
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};
    static int [][] map;
    
    public int solution(int[][] land) {
        max = 0;
        
        n = land.length;
        m = land[0].length;
        
        id = new HashMap<>();
        visited = new boolean [n][m];
        map = new int [n][m];
        int currId = 1;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(land[i][j] == 0 || visited[i][j]) continue;
                id.put(currId, bfs(i, j, currId, land));
                currId++;
            }
        }
        
        for(int j = 0; j < m; j++){
            Set<Integer> currIdSet = new HashSet<>();
            for(int i = 0; i < n; i++){
                if(map[i][j] != 0) currIdSet.add(map[i][j]);
            }
            
            int total = 0;
            for(int curr : currIdSet){
                total += id.get(curr);                
            }
            
            max = Math.max(total, max);
        }
        
        return max;
    }
    
    public static int bfs(int sx, int sy, int currId, int [][] land){
        Queue<int []> q = new ArrayDeque<>();
        q.offer(new int [] {sx, sy});
        visited[sx][sy] = true;
        int cnt = 0;
        
        while(!q.isEmpty()){
            int [] curr = q.poll();
            
            int cx = curr[0];
            int cy = curr[1];
            map[cx][cy] = currId;
            cnt++;
            
            for(int i = 0; i < 4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || land[nx][ny] == 0) continue;
                
                visited[nx][ny] = true;
                
                q.offer(new int [] {nx, ny});
            }
        }
        
        return cnt;
    }
    
}
