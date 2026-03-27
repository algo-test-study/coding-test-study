"""
O(n*m^2), dp
"""
def solution(land):
    answer = 0
    
    n = len(land)
    m = len(land[0])

    dp = [[0]*m for _ in range(n)]
    
    dp[0] = land[0]
    
    for i in range(1, n):
        for j in range(m):
            dp[i][j] = max(dp[i-1][:j] + dp[i-1][j+1:]) + land[i][j]
    
    return max(dp[n-1])
