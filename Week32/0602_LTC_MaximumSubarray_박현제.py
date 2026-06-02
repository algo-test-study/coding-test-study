"""
[-2, -1, -4, 0, -1, 1, 2, -3, 1] // 2 - 0이 최대
누적합 후 차례로 확인, 그 왼쪽에서 최솟값 찾기 / 갱신
누적합, O(N)
"""
class Solution(object):
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        
        n = len(nums)
        sums = [0] * n
        sums[0] = nums[0]
        for i in range(1, n):
            sums[i] = sums[i-1] + nums[i]

        ans = -float('inf')
        min_v = 0

        for curr in sums:
            ans = max(ans, curr - min_v)

            if min_v > curr:
                min_v = curr
        
        return ans
            
