"""
?: 인접하지 않고 털 수 있는 최대 금액
a: DP
- dp = nums[i] + dp[i-2] or dp[i-1]
t: O(N)
"""
class Solution(object):
    def rob(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        n = len(nums)
        dp = [0] * n

        dp[0] = nums[0]
        if n >= 2:
            dp[1] = max(nums[0], nums[1])
    
        for i in range(2, n):
            dp[i] = max(dp[i-1], dp[i-2] + nums[i])

        return dp[n-1]
