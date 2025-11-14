/*
- 연속해서 밟지 않을 경우 최대 점수

알고리즘
- 완탐시 O(4^100000): 불가능
- 이전까지의 최댓값을 계속 저장해서 이용할 수 있으므로 dp
- dp[n][4] -> dp[i][j] = i번째 행의 j 열 땅까지의 최댓값
- dp[n-1][0 ~ 3]의 최댓값이 답.
- dp[i][j] = dp[i-1][!j] + 현재 땅의 값

시간복잡도
- O(4*4*N)

*/
import java.util.*;
class Solution {
    int solution(int[][] land) {
        int n = land.length;
        int [][] dp = new int [n+1][4];
        
        for(int j = 0; j < 4; j++){           
            dp[0][j] = land[0][j];
        }
        
        for(int i = 1; i < n; i++){
            for(int j = 0; j < 4; j++){
                for(int k = 0; k < 4; k++){
                    if(k == j) continue;
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][k]);
                }
                dp[i][j] += land[i][j];
            }
        }
        
        int max = 0;
        
        for(int j = 0; j < 4; j++){
            max = Math.max(max, dp[n-1][j]);
        }
        
        return max;
    }
}