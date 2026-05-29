
"""
멀티소스 bfs, O(nm)
"""
from collections import deque
class Solution(object):
    def updateMatrix(self, mat):
        """
        :type mat: List[List[int]]
        :rtype: List[List[int]]
        """
        
        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]

        m = len(mat)
        n = len(mat[0])

        q = deque()

        visited = [[False] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                if mat[i][j] == 0:
                    q.append((i,j))
                    visited[i][j] = True
        while q:
            cx, cy = q.popleft()

            for d in range(4):
                nx, ny = cx + dx[d], cy + dy[d]

                if 0 <= nx < m and 0 <= ny < n and not visited[nx][ny]:
                    visited[nx][ny] = True
                    mat[nx][ny] = mat[cx][cy] + 1
                    q.append((nx, ny))
    
        return mat
                
        
        
