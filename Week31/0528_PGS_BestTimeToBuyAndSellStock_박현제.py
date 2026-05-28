class Solution(object):
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """

        min_p = float('inf')
        ans = 0

        for p in prices:
            min_p = min(min_p, p)

            profit = p - min_p
            ans = max(ans, profit)
            
        return ans
