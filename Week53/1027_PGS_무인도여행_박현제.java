
import java.util.*;
class Solution {
    static boolean [][] visited;
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};
    static int n, m;

    public int[] solution(String[] maps) {
        List<Integer> ans = new ArrayList<>();

        n = maps.length;
        m = maps[0].length();
        visited = new boolean [n][m];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(visited[i][j] || maps[i].charAt(j) == 'X') continue;
                ans.add(dfs(i, j, maps));
            }
        }
        
        Collections.sort(ans);
        
        return ans.isEmpty() ? new int [] {-1} : ans.stream().mapToInt(i -> i).toArray();
    }
    
    static int dfs(int cx, int cy, String [] maps){
        int cnt = maps[cx].charAt(cy) - '0';
        
        visited[cx][cy] = true;
        
        for(int i = 0; i < 4; i++){
            int nx = cx + dx[i];
            int ny = cy + dy[i];
            
            if(nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || maps[nx].charAt(ny) == 'X') continue;
            
            cnt += dfs(nx, ny, maps);
            
        }
        
        return cnt;
    }
}
