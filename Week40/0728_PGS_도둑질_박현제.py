"""
도둑이 훔칠 수 있는 돈의 최댓값

첫집을 턴다 -> 마지막 집 안턴다
첫집을 안 턴다 -> 마지막 집 상관 없다

dp[i][j] = 첫번째 집을 털고/안 터는 상황에서, i번째 집까지 돈의 최댓값
턴다: j = 1 / 안 턴다: j = 0
"""

def solution(money):
    n = len(money)
    
    dp = [[0] * 2 for _ in range(n)]
    
    dp[1][0] = money[1]
    dp[0][1] = money[0]
    dp[1][1] = max(money[1], money[0])
    
    for i in range(2, n):
        for j in range(2):
            dp[i][j] = max(dp[i-1][j], money[i] + dp[i-2][j])
        
    return max(dp[n-1][0], dp[n-2][1])
