import java.util.*;

class Solution {
    public int solution(String[] strs, String t) {
        Set<String> set = new HashSet<>(Arrays.asList(strs));

        int n = t.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int len = 1; len <= 5; len++) {
                if (i - len < 0) continue;

                String sub = t.substring(i - len, i);

                if (set.contains(sub) && dp[i - len] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - len] + 1);
                }
            }
        }

        int answer = (dp[n] == Integer.MAX_VALUE) ? -1 : dp[n];
        return answer;
    }
}
