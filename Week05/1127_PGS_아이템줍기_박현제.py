
"""
답 참고함
알고리즘
- 2배 후 외/내부 나눠 표시
- bfs

시간복잡도
O(MAX^2)
"""
from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):
  
    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]
  
    MAX = 102
    grid = [[-1] * MAX for _ in range(MAX)]
    
    for r in rectangle:
        x1, y1, x2, y2 = map(lambda x: x * 2, r)
        
        for i in range(x1, x2 + 1):
            for j in range(y1, y2 + 1):
                if x1 < i < x2 and y1 < j < y2:
                    grid[i][j] = 0
                elif grid[i][j] != 0:
                    grid[i][j] = 1

    q = deque()
    q.append((characterX * 2, characterY * 2))
  
    visited = [[-1] * MAX for _ in range(MAX)]
    visited[characterX*2][characterY*2] = 0
  
    while q:
      
        x, y = q.popleft()
        
        if x == itemX*2 and y == itemY*2:
            return visited[x][y]//2
        
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            
            if 0 <= nx < MAX and 0 <= ny < MAX:
                if grid[nx][ny] == 1 and visited[nx][ny] == -1:
                  
                    visited[nx][ny] = visited[x][y] + 1
                    q.append((nx, ny))

  
    return 0
