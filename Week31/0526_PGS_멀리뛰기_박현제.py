"""
dp, 피보나치 / O(n)
"""
def solution(n):
    
    MOD = 1234567
    
    dp = [0] * (n+2) # n=1 방지
    
    dp[1] = 1
    dp[2] = 2
    
    for i in range(3, n+1):
        dp[i] = (dp[i-1] + dp[i-2])%MOD
    
    return dp[n]
    
