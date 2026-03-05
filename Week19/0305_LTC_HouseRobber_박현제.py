"""
알고리즘
- dp
- 인접 하지 않은 것 -> dp[i] = max(now + dp[i-2], dp[i-1])
- 엣지: n <= 1일때 미리 종료 안하면 OOR
시간복잡도
- O(n)

"""

class Solution:
    def rob(self, nums: List[int]) -> int:
        n = len(nums)
        dp = [0] * n

        if n <= 1:
            return nums[0]

        dp[0] = nums[0]
        dp[1] = max(nums[0], nums[1])

        for i in range(2, n):
            dp[i] = max(nums[i] + dp[i-2], dp[i-1])

        return dp[n-1]
