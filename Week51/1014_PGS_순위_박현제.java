class Solution {
    public int solution(int n, int[][] results) {
        boolean [][] g = new boolean[n+1][n+1];
        
        for(int [] v : results){
            g[v[0]][v[1]] = true;
        }
        
        for(int m=1; m<=n; m++){
            for(int s = 1; s <= n; s++){
                for(int e = 1; e <= n; e++){
                    if(g[s][m] && g[m][e]) g[s][e] = true;
                }
            }
        }
        
        int cnt, ans=0;
        for(int i = 1; i <= n; i++){
            cnt = 0;
            for(int j = 1; j <= n; j++){
                if(g[i][j] || g[j][i]) cnt++;
            }
            if(cnt == n - 1) ans++;
        }
        return ans;
    }
}
