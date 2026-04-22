class Solution {
    public int solution(int[][] triangle) {

        int n = triangle.length;
        int [][] dp = new int [n][n];
        
        dp[0][0] = triangle[0][0];
        
        for(int i=1; i<n; i++){
            for(int j=0; j<i+1; j++){
                if(j == 0){
                    dp[i][j] += triangle[i][j] + dp[i-1][j];
                    continue;
                }
                
                dp[i][j] = triangle[i][j] + Math.max(dp[i-1][j-1], dp[i-1][j]);
            }
            
        }
        
        int ans = 0;
        for(int num : dp[n-1]){
            ans = Math.max(num, ans);
        }
        return ans;
    }
}
