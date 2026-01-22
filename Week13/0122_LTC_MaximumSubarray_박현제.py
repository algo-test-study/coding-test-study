"""
알고리즘
- 누적합
- 최대 - 최소의 최댓값
5 9 8 15 23
-2 -1 -4 0 -1 1 2 -3 1

시간복잡도
- O(N)
"""
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        n = len(nums)
        sums = [0]*n
        sums[0] = nums[0]
        for i in range(1, len(nums)):
            sums[i] = sums[i-1] + nums[i]
        
        min_v = 0
        ans = -10**4
        for v in sums:
            ans = max(v - min_v, ans)
            if min_v > v:
                min_v = v
        return ans
