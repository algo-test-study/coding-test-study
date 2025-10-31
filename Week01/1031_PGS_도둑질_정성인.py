def get_max(money):
    n = len(money)
    
    dp = [-1] * n
    dp[0] = money[0]
    dp[1] = max(money[0], money[1])
    
    for i in range(2, n):
        dp[i] = max(dp[i-1], money[i] + dp[i-2])
    return dp[-1]

def solution(money):
    if len(money) == 3:
        return max(money)
    
    res1 = get_max(money[:-1])
    res2 = get_max(money[1:])
    
    return max(res1, res2)
