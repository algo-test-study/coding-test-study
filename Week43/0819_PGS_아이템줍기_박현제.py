"""
테두리: 1 / 내부, 못감: 0
특정 점 -> 노드로 변경 -> 2배 해야댐
한 점에서의 최단 경로 -> bfs
"""

from collections import deque
def solution(rectangle, characterX, characterY, itemX, itemY):

    n = 102 
    grid = [[False] * n for _ in range(n)]
    
    sx, sy = characterX * 2, characterY * 2
    ex, ey = itemX * 2, itemY * 2

    dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]
    
    for lx, ly, rx, ry in rectangle:
        lx, ly, rx, ry = lx*2, ly*2, rx*2, ry*2
        for x in range(lx, rx + 1):
            for y in range(ly, ry + 1):
                if x == lx or x == rx or y == ly or y == ry:
                    grid[x][y] = True

    for lx, ly, rx, ry in rectangle:
        lx, ly, rx, ry = lx*2, ly*2, rx*2, ry*2
        for x in range(lx + 1, rx):
            for y in range(ly + 1, ry):
                grid[x][y] = False
            
    q = deque()
    q.append((sx, sy, 0))
    grid[sx][sy] = False
    
    while q:
        cx, cy, cnt = q.popleft()
        
        if cx == ex and cy == ey:
            return cnt // 2
        
        for i in range(4):
            nx, ny = cx + dx[i], cy + dy[i]
            
            if 0 <= nx < n and 0 <= ny < n:
                if not grid[nx][ny]:
                    continue
                    
                grid[nx][ny] = False
                q.append((nx, ny, cnt + 1))
    
    return -1
