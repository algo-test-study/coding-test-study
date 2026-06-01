import java.util.*;

class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;

        Job[] jobs = new Job[n];

        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }

        Arrays.sort(jobs, (a, b) -> a.end - b.end);

        int[] dp = new int[n];

        dp[0] = jobs[0].profit;

        for (int i = 1; i < n; i++) {
            int includeProfit = jobs[i].profit;

            int prevIndex = findLastNonOverlappingJob(jobs, i);

            if (prevIndex != -1) {
                includeProfit += dp[prevIndex];
            }

            int excludeProfit = dp[i - 1];

            dp[i] = Math.max(includeProfit, excludeProfit);
        }

        return dp[n - 1];
    }

    private int findLastNonOverlappingJob(Job[] jobs, int currentIndex) {
        int left = 0;
        int right = currentIndex - 1;

        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (jobs[mid].end <= jobs[currentIndex].start) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    static class Job {
        int start;
        int end;
        int profit;

        Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
}
