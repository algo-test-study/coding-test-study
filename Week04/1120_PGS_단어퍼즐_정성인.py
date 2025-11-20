def solution(strs, t):
    # dp[x] = x번째까지 최소 수
    INF = float('inf')
    dp = [INF] * (len(t)+1)
    dp[0] = 0
    
    for i in range(len(t)+1):
        if dp[i] == INF:
            continue
        for w in strs:
            if t.startswith(w, i):
                dp[i+len(w)] = min(dp[i+len(w)], dp[i]+1)
    
    return dp[len(t)] if dp[len(t)] != INF else -1
