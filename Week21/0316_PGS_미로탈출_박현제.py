"""
bfs, O(nm)
"""
from collections import deque



def solution(maps):
    n = len(maps)
    m = len(maps[0])

    for i in range(n):
        for j in range(m):
            if maps[i][j] == 'S':
                sx, sy = i, j
            elif maps[i][j] == 'L':
                lx, ly = i, j
            elif maps[i][j] == 'E':
                ex, ey = i, j

    def bfs(sx, sy, tx, ty):
        visited = [[False] * m for _ in range(n)]
        q = deque()
        q.append((sx, sy, 0))
        
        
        visited[sx][sy] = True

        dx = [1, -1, 0, 0]
        dy = [0, 0, 1, -1]

        while q:
            cx, cy, dist = q.popleft()

            if (cx, cy) == (tx, ty):
                return dist

            for i in range(4):
                nx = cx + dx[i]
                ny = cy + dy[i]

                if 0 <= nx < n and 0 <= ny < m:
                    if not visited[nx][ny] and maps[nx][ny] != 'X':
                        visited[nx][ny] = True
                        q.append((nx, ny, dist + 1))

        return -1

    d1 = bfs(sx, sy, lx, ly)
    if d1 == -1:
        return -1

    d2 = bfs(lx, ly, ex, ey)
    if d2 == -1:
        return -1
    
    ans = d1 + d2
    return ans
