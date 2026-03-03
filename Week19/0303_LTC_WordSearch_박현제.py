"""
알고리즘
- 완탐
- 첫글자 일치만 담고 dfs

시간복잡도
- O(nm)
"""
class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]

        n = len(board)
        m = len(board[0])

        visited = [[False] * m for _ in range(n)]

        def dfs(cx, cy, depth):

            if depth == len(word) - 1:
                return True

            for i in range(4):
                nx, ny = cx + dx[i], cy + dy[i]
                if 0 <= nx < n and 0 <= ny < m and not visited[nx][ny] and word[depth + 1] == board[nx][ny]:
                    visited[nx][ny] = True            
                    if dfs(nx, ny, depth + 1):
                        return True
                    visited[nx][ny] = False
            return False
        for i in range(n):
            for j in range(m):
                if word[0] == board[i][j]:
                    visited[i][j] = True
                    if dfs(i, j, 0):
                        return True
                    visited[i][j] = False
            
        return False
