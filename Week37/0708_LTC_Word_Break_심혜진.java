import java.util.*;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        int maxLen = 0;
        for (String w : wordDict) {
            if (w.length() > maxLen) maxLen = w.length();
        }

        for (int i = 1; i <= n; i++) {
            int start = Math.max(0, i - maxLen);
            for (int j = start; j < i; j++) {
                if (!dp[j]) continue;
                if (dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
