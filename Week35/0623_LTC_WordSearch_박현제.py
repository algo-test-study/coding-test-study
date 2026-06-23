"""
dfs, 백트래킹
- O(NM*4^L)
"""

class Solution(object):
    def exist(self, board, word):
        """
        :type board: List[List[str]]
        :type word: str
        :rtype: bool
        """
        m = len(board)
        n = len(board[0])

        dx, dy = [0,0,-1,1], [1,-1,0,0]

        def dfs(cx, cy, depth):
            if board[cx][cy] != word[depth]:
                return False

            if depth == len(word) - 1:
                return True

            visited[cx][cy] = True
            
            for i in range(4):
                nx, ny = cx + dx[i], cy + dy[i]

                if 0 <= nx < m and 0 <= ny < n and not visited[nx][ny]:
                    if dfs(nx, ny, depth+1):
                        return True

            visited[cx][cy] = False

            return False

        visited = [[False] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                if dfs(i, j, 0):
                    return True
        return False
