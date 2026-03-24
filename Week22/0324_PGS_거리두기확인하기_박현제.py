"""
bfs, O(n^2)
"""
from collections import deque
def solution(places):
    answer = []
    
    n = 5
  
    dx = [-1,1,0,0]
    dy = [0,0,-1,1]
  
    def bfs(sx, sy, d, place):
        q = deque()
        visited[sx][sy] = True
        
        q.append((sx, sy, 0))
        
        while q:
            cx, cy, cd = q.popleft()
            
            if cd >= d:
                continue
            
            for i in range(4):
                nx, ny = cx + dx[i], cy + dy[i]
                
                if 0 <= nx < n and 0 <= ny < n and place[nx][ny] != 'X' and not visited[nx][ny]:
                    if place[nx][ny] == 'P':
                        return False
                    visited[nx][ny] = True
                    q.append((nx, ny, cd+1))
        
        return True
    
    def is_valid(place):
        for i in range(n):
            for j in range(n):
                if place[i][j] == 'P' and not visited[i][j]:
                    if not bfs(i,j,2,place):
                        return 0
        return 1
    
    for place in places:
        visited = [[False] * n for i in range(n)]
        answer.append(is_valid(place))
                            
    return answer
