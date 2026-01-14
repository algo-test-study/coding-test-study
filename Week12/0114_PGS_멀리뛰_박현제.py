"""
알고리즘
- 끝에 도달하는 방법 개수 구하기
- dp[i] -> 1 2 3 5

- dp[i][0] = dp[i-1][1] + dp[i-2]
- ans % 1234567

시간복잡도
- O(n)

"""

def solution(n):
    mod = 1234567
    dp = [0 for i in range(n + 1)]
    
    dp[1] = 1
    dp[2] = 2
    
    for i in range(3, n + 1):
        dp[i] = (dp[i-1] + dp[i-2])%mod
        
    return dp[n]%mod
