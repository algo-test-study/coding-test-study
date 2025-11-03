/*
출발 > 레버
최소 시간

알고리즘
- bfs: s -> l -> e
- s -> l 혹은 l -> e가 안되면 -1
- 아니면 s -> l + l -> e

시간 복잡도
- O(nm)
*/
import java.util.*;
class Solution {
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};
    static int n, m;
    static String [] maps;

    public int solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        this.maps = maps;
        
        int sx = 0;
        int sy = 0;
        int ex = 0;
        int ey = 0;
        int lx = 0;
        int ly = 0;
      
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                char curr = maps[i].charAt(j);
                if(curr == 'S'){
                    sx = i;
                    sy = j;
                }else if(curr == 'L'){
                    lx = i;
                    ly = j;
                }else if(curr == 'E'){
                    ex = i;
                    ey = j;
                }
            }
        }
            
        int sToL = bfs(sx, sy, lx, ly);
        int lToE = bfs(lx, ly, ex, ey);
            
        if(sToL == -1 || lToE == -1){
            return -1;
        }
        
        return sToL + lToE;
    }
    
    public static int bfs(int sx, int sy, int ex, int ey){
        Queue<int []> q = new LinkedList<>();
        
        q.offer(new int [] {sx, sy});
        
        boolean [][] visited = new boolean[n][m];
        
        visited[sx][sy] = true;
        
        int cnt = 0;
        
        while(!q.isEmpty()){
            
            int qSize = q.size();
            
            while(qSize-- > 0){                
                int [] curr = q.poll();
                int cx = curr[0];
                int cy = curr[1];

                if(cx == ex && cy == ey){
                    return cnt;
                }
                
                for(int i=0; i<4; i++){
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];
                    
                    if(nx >= n || ny >= m || nx < 0 || ny < 0) continue;

                    char nc = maps[nx].charAt(ny);
                    if(nc == 'X' || visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    q.offer(new int [] {nx, ny});
                }
            }
            
            cnt++;
        }
        
        return -1;
    }
}
