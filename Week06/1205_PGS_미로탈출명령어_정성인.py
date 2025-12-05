import sys
sys.setrecursionlimit(5000)

answer = None

def dfs(x, y, r, c, path, n, m, k, depth):
    global answer
    
    if answer is not None:
        return
    
    if k == depth:
        if (x, y) == (r, c):
            answer = path
        return
    
    left = abs(x-r)+abs(y-c)
    if k-depth < left:
        return
    if (k-depth - left) % 2 != 0:
        return
    
    dx, dy = [1, 0, 0, -1], [0, -1, 1, 0]
    direction = ['d', 'l', 'r', 'u']
    
    for i in range(4):
        nx, ny = x+dx[i], y+dy[i]
        
        if not (1<=nx<=n and 1<=ny<=m):
            continue
        dfs(nx, ny, r, c, path+direction[i], n, m, k, depth+1)
        
def solution(n, m, x, y, r, c, k):
    dfs(x, y, r, c, '', n, m, k, 0)
    return answer if answer is not None else 'impossible'
