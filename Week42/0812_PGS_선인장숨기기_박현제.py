"""
O(mnlog(mn))
"""

def solution(m, n, h, w, drops):
    l = len(drops)
    t = [[l] * n for _ in range(m)]
    for i, (r, c) in enumerate(drops):
        t[r][c] = i

    answer = [0, 0]

    def is_valid(k):
        nonlocal answer
        grid = [[0] * (n + 1) for _ in range(m + 1)]
        
        for i in range(m):
            for j in range(n):
                bad = 1 if t[i][j] < k else 0
                grid[i+1][j+1] = bad + grid[i][j+1] + grid[i+1][j] - grid[i][j]

        for i in range(h, m + 1):
            for j in range(w, n + 1):
                if grid[i][j] - grid[i-h][j] - grid[i][j-w] + grid[i-h][j-w] == 0:
                    answer = [i - h, j - w]
                    return True
        return False

    s, e = 0, l
    
    while s <= e:
        mid = (s + e) // 2
        if is_valid(mid):
            s = mid + 1
        else:
            e = mid - 1

    return answer
