from collections import deque


def solution(rectangle, characterX, characterY, itemX, itemY):
    MAX = 102
    board = [[0] * MAX for _ in range(MAX)]

    for x1, y1, x2, y2 in rectangle:
        x1, y1, x2, y2 = x1 * 2, y1 * 2, x2 * 2, y2 * 2

        for x in range(x1, x2 + 1):
            for y in range(y1, y2 + 1):
                if x in (x1, x2) or y in (y1, y2):
                    if board[x][y] != 2:
                        board[x][y] = 1
                else:
                    board[x][y] = 2

    sx, sy = characterX * 2, characterY * 2
    ex, ey = itemX * 2, itemY * 2

    q = deque([(sx, sy, 0)])
    visited = [[False] * MAX for _ in range(MAX)]
    visited[sx][sy] = True

    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]

    while q:
        x, y, dist = q.popleft()

        if (x, y) == (ex, ey):
            return dist // 2

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if 0 <= nx < MAX and 0 <= ny < MAX:
                if not visited[nx][ny] and board[nx][ny] == 1:
                    visited[nx][ny] = True
                    q.append((nx, ny, dist + 1))
