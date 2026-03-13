"""
dp, O(n)
"""
def solution(money):
    answer = 0
    
    n = len(money)

    dp1 = [0]*n
    dp0 = [0]*n
    
    dp1[0] = dp1[1] = money[0] # 0 번째 선택
    dp0[1] = money[1] # 0 번쨰 미선택
    
    
    for i in range(2, n-1):
        dp1[i] = max(dp1[i-1], dp1[i-2] + money[i])
    
    for i in range(2, n):
        dp0[i] = max(dp0[i-1], dp0[i-2] + money[i])

        
    return max(dp1[n-2], dp0[n-1])
