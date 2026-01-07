"""
알고리즘
- dp[i][j] = max(dp[i][j-1], dp[i][j]) + now

시간복잡도
O(n*len(triangle[n]))
"""

def solution(triangle):
    answer = 0
    n = len(triangle)
    dp = [[0] * (n + 1) for _ in range(n + 1)]
    
    for i in range(1, n + 1):
        for j in range(1, i + 1):
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-1]) + triangle[i-1][j-1]
    
    for i in range(1, n + 1):
        answer = max(answer, dp[n][i])
        
    return answer
