/*
- 인접 두 집 털면 > 경보
- 경보 안 울리는 조건에서 돈의 최댓값 구하기
- 상태: 턺/안 턺

알고리즘
- 완탐x: 집이 10^6개 > 2^(10^6)
- 마지막 집 - 첫 번째 집 연결: 첫집 털림/안털림
- dp[i번째 집]= i번째 집까지 돈의 최댓값
- dp[i] = max(dp[i-1], dp[i-2] + 현재 돈)

시간복잡도
- O(n)
*/
import java.util.*;
class Solution {
    public int solution(int[] money) {
        int n = money.length;
        int [] dp = new int [n];
        
        int max = 0;
        //첫 집 털음 X
        dp[0] = 0;
        dp[1] = money[1];
        for(int i=2; i<n; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i]);
        }
        
        max = dp[n-1];
        
        //첫 집 털음 O
        dp[0] = money[0];
        dp[1] = dp[0];
        for(int i=2; i<n; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i]);
        }
        
        max = Math.max(max, dp[n-2]);
        
        return max;
    }
}
