"""
탐색, O(n^2)
"""
import sys
sys.setrecursionlimit(10**6)
def solution(n, m, x, y, r, c, k):
    answer = ''
    
    is_end = False
    
    dirs = ['d', 'l', 'r', 'u']
    dx = [1, 0, 0, -1]
    dy = [0, -1, 1, 0]
    
    def dfs(cx, cy, depth, path):
        nonlocal is_end, answer
        
        if is_end:
            return

        remaining = k - depth
        dist = abs(cx - r) + abs(cy - c)

        if remaining < dist or (remaining - dist) % 2 != 0:
            return
        
        if depth >= k:
            if cx == r and cy == c:
                answer = ''.join(path)
                is_end = True
            return
        
        for i in range(4):
            nx, ny = cx + dx[i], cy + dy[i]
            
            if 0 < nx <= n and 0 < ny <= m:
                d = dirs[i]
                path.append(d)
                dfs(nx, ny, depth+1, path)
                path.pop()
    
    dist = abs(x - r) + abs(y - c)
    if k < dist:
        return "impossible"
    
    dfs(x, y, 0, [])
    
    return "impossible" if answer == '' else answer
