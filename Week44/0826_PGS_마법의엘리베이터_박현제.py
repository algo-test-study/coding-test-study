"""
O(2^n)
"""
def solution(storey):

    min_v = float('inf')
    
    def dfs(num):
        if num < 10:
            return min(num, 10 - num + 1)
        d = num % 10
        min_v = min(d + dfs(num // 10), (10 - d) + dfs(num // 10 + 1))
        return min_v
    ans = dfs(storey)
    return ans
