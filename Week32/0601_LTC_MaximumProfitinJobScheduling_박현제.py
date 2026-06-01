"""
dp, 이분탐색O(n log n)
"""
from bisect import bisect_right
class Solution(object):
    def jobScheduling(self, startTime, endTime, profit):
        """
        :type startTime: List[int]
        :type endTime: List[int]
        :type profit: List[int]
        :rtype: int
        """
        jobs = zip(startTime, endTime, profit)

        jobs.sort(key = lambda x: x[1])

        ends = [job[1] for job in jobs]

        n = len(jobs)

        dp = [0] * (n+1)

        for i in range(1, n+1):
            s, e, p = jobs[i-1]
            
            j = bisect_right(ends, s)

            dp[i] = max(dp[i - 1], dp[j] + p)
        return dp[n]
