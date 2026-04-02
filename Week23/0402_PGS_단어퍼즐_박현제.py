"""
dp, O(N*5)

"""
def solution(strs, t):
    INF = float('inf')
    n = len(t)
    dp = [INF] * (n+1)
    dp[0] = 0
    set_strs = set(strs)
    
    for i in range(n):
        if dp[i] == INF:
            continue
        
        for l in range(1, 6):
            if i + l <= n and t[i:i + l] in set_strs:
                dp[i + l] = min(dp[i] + 1, dp[i + l])
    
    return dp[n] if dp[n] != INF else -1
                
