"""
amount 를 만들 수 있는 최소 동전 개수
만들 수 없으면 -1

1. 일단 공약수가 아니니까 그리디는 아님
2. dp
- dp[i] : i 값을 만드는 최소 동전 개수
- O(amount)
"""

class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:

        INF = float('inf')

        dp = [INF] * (amount + 1)

        dp[0] = 0

        for i in range(1, amount+1):
            
            for coin in coins:
                if i >= coin:
                    dp[i] = min(dp[i], dp[i - coin] + 1)

        return dp[amount] if INF != dp[amount] else -1
