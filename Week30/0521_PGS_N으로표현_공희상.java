import java.util.*;
class Solution {
    public int solution(int N, int number) {        
        Set<Integer>[] dp = new HashSet[9];
        for (int i = 1; i <= 8; i++) {
            dp[i] = new HashSet();
            
            int repeated = 0;
            for (int j = 0; j < i; j++) {
                repeated = repeated * 10 + N;
            }
            dp[i].add(repeated);
            
            for (int j = 1; j < i; j++) {
                Set<Integer> lefts = dp[j];
                Set<Integer> rights = dp[i-j];
                
                for (int left : lefts) {
                    for (int right : rights) {
                        dp[i].add(left+right);
                        dp[i].add(left-right);
                        dp[i].add(left*right);
                    
                        if (right != 0) {
                            dp[i].add(left/right);
                        }
                    }
                }
            }
        
            if (dp[i].contains(number)) {
                return i;
            }
        }
        
        return - 1;
    }
}
