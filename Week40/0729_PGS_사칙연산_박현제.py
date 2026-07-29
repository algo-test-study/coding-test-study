"""
n^3
"""
def solution(arr):
    nums = [int(x) for x in arr[::2]]
    oper = arr[1::2]
    
    n = len(nums)
    
    dp_max = [[0] * n for _ in range(n)]
    dp_min = [[0] * n for _ in range(n)]
    
    for i in range(n):
        dp_max[i][i] = dp_min[i][i] = nums[i]

    for l in range(2, n +1):
        for i in range(n - l + 1):
            j = i + l - 1
            max_v = float('-inf')
            min_v = float('inf')
            for k in range(i, j):
                if oper[k] == '+':
                    a = dp_max[i][k] + dp_max[k + 1][j]
                    b = dp_min[i][k] + dp_min[k + 1][j]
                else:
                    a = dp_max[i][k] - dp_min[k + 1][j]
                    b = dp_min[i][k] - dp_max[k + 1][j]
                max_v = max(max_v, a)
                min_v = min(min_v, b)
                
            dp_max[i][j] = max_v
            dp_min[i][j] = min_v

    return dp_max[0][n-1]
