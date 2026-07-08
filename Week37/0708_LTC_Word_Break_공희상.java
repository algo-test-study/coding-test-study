import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);

        int maxWordLength = 0;

        for (String word : wordDict) {
            maxWordLength = Math.max(maxWordLength, word.length());
        }

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int end = 1; end <= s.length(); end++) {
            int minStart = Math.max(0, end - maxWordLength);

            for (int start = minStart; start < end; start++) {
                if (!dp[start]) {
                    continue;
                }

                if (words.contains(s.substring(start, end))) {
                    dp[end] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}
