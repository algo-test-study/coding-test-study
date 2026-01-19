"""
알고리즘
- 완탐 ->  O(N^2)로 불가
- 지금까지 본 가장 작은 가격에 팔기

시간복잡도
- O(N)
"""
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        ans = 0
        n = len(prices)
        min_v = 10**4
        for p in prices:
            min_v = min(min_v, p)
            profit = p - min_v
            if profit > ans:
                ans = profit
        return ans
        
