import java.util.*;

class Solution {
    public int solution(int[][] cost, int[][] hint) {
        int n = cost.length;
        int bundleCount = n - 1;
        int maxState = 1 << bundleCount;

        long[] dp = new long[maxState];
        Arrays.fill(dp, Long.MAX_VALUE);

        dp[0] = 0;

        for (int stage = 0; stage < n; stage++) {
            long[] nextDp = new long[maxState];
            Arrays.fill(nextDp, Long.MAX_VALUE);

            for (int mask = 0; mask < maxState; mask++) {
                if (dp[mask] == Long.MAX_VALUE) {
                    continue;
                }

                int hintCount = 0;

                for (int bundle = 0; bundle < stage; bundle++) {
                    if ((mask & (1 << bundle)) == 0) {
                        continue;
                    }

                    for (int j = 1; j < hint[bundle].length; j++) {
                        if (hint[bundle][j] == stage + 1) {
                            hintCount++;
                        }
                    }
                }

                hintCount = Math.min(hintCount, n - 1);

                long totalCost = dp[mask] + cost[stage][hintCount];

                nextDp[mask] = Math.min(nextDp[mask], totalCost);

                if (stage < n - 1) {
                    int newMask = mask | (1 << stage);
                    long newTotalCost = totalCost + hint[stage][0];

                    nextDp[newMask] = Math.min(nextDp[newMask], newTotalCost);
                }
            }

            dp = nextDp;
        }

        long answer = Long.MAX_VALUE;

        for (long value : dp) {
            answer = Math.min(answer, value);
        }

        return (int) answer;
    }
}
