"""
알고리즘
- 전체 점에 대해 bfs
시간복잡도
- O(mn)
"""
from collections import deque
class Solution:
    def updateMatrix(self, mat: List[List[int]]) -> List[List[int]]:
        m, n = len(mat), len(mat[0])
        dist = [[float('inf')] * n for _ in range(m)]
        q = deque()
        
        for i in range(m):
            for j in range(n):
                if mat[i][j] == 0:
                    dist[i][j] = 0
                    q.append((i, j))
        
        dx = [0, 0, 1, -1]
        dy = [1, -1, 0, 0]
        
        while q:
            x, y = q.popleft()
            for i in range(4):
                
                nx, ny = x + dx[i], y + dy[i]

                if 0 <= nx < m and 0 <= ny < n:
                    if dist[nx][ny] > dist[x][y] + 1:
                        dist[nx][ny] = dist[x][y] + 1
                        q.append((nx, ny))
        
        return dist
