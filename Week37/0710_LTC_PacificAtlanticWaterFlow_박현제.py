"""
O(m x n)
"""
from collections import deque
class Solution(object):
    def pacificAtlantic(self, heights):
        """
        :type heights: List[List[int]]
        :rtype: List[List[int]]
        """
        n = len(heights)
        m = len(heights[0])

        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]

        def bfs(s):
            visited = [[False] * m for _ in range(n)]

            q = deque()

            for x, y in s:
                if not visited[x][y]:
                    visited[x][y] = True
                    q.append((x, y))

            while q:
                cx, cy = q.popleft()

                for i in range(4):
                    nx = cx + dx[i]
                    ny = cy + dy[i]

                    if 0 <= nx < n and 0 <= ny < m and not visited[nx][ny]:
                        if heights[nx][ny] >= heights[cx][cy]:
                            visited[nx][ny] = True
                            q.append((nx, ny))

            return visited

        pacific_s = []
        atlantic_s = []

        for i in range(n):
            pacific_s.append((i, 0))
            atlantic_s.append((i, m - 1))

        for j in range(m):
            pacific_s.append((0, j))
            atlantic_s.append((n - 1, j))

        pacific = bfs(pacific_s)
        atlantic = bfs(atlantic_s)

        ans = []

        for i in range(n):
            for j in range(m):
                if pacific[i][j] and atlantic[i][j]:
                    ans.append([i, j])

        return ans
