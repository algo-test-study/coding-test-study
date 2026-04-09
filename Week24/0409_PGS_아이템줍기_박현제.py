
"""
bfs, O(n^2)
"""
from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):
    
    n = 102
    g = [[-1] * n for _ in range(n)]
    
    for r in rectangle:
        x1, y1, x2, y2 = [v * 2 for v in r]
        
        for i in range(x1, x2 + 1):
            for j in range(y1, y2 + 1):
                if x1 < i < x2 and y1 < j < y2:
                    g[i][j] = 0
                elif g[i][j] != 0:
                    g[i][j] = 1

    q = deque()
    
    q.append((characterX * 2, characterY * 2))
    visited = [[-1] * n for _ in range(n)]
    visited[characterX * 2][characterY * 2] = 0
    
    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]
    
    while q:
        cx, cy = q.popleft()
        
        if cx == itemX * 2 and cy == itemY * 2:
            return visited[cx][cy] // 2
        
        for i in range(4):
            nx, ny = cx + dx[i], cy + dy[i]
            
            if 0 <= nx < n and 0 <= ny < n and g[nx][ny] == 1 and visited[nx][ny] == -1:
                    visited[nx][ny] = visited[cx][cy] + 1
                    q.append((nx, ny))
                    
    return 0
