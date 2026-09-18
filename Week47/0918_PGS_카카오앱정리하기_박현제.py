
from collections import deque
def solution(board, commands):
    n = len(board)
    m = len(board[0])
    dr = [0, 0, 1, 0, -1]
    dc = [0, 1, 0, -1, 0]

    def move(start, dir):
        nonlocal board

        moving = set([start])
        q = deque([start])

        while True:
            while q:
                curr = q.popleft()

                for r in range(n):
                    for c in range(m):
                        if board[r][c] != curr:
                            continue

                        nr = (r + dr[dir]) % n
                        nc = (c + dc[dir]) % m

                        nxt = board[nr][nc]

                        if nxt != 0 and nxt not in moving:
                            moving.add(nxt)
                            q.append(nxt)

            nxt_board = [[0] * m for _ in range(n)]

            for r in range(n):
                for c in range(m):
                    app = board[r][c]

                    if app == 0:
                        continue

                    if app in moving:
                        nr = (r + dr[dir]) % n
                        nc = (c + dc[dir]) % m
                        nxt_board[nr][nc] = app
                    else:
                        nxt_board[r][c] = app

            board = nxt_board

            moving = set()

            if dir == 1 or dir == 3:
                for r in range(n):
                    app = board[r][0]

                    if app == 0 or board[r][m - 1] != app:
                        continue

                    split = False

                    for c in range(m):
                        if board[r][c] != app:
                            split = True
                            break

                    if split and app not in moving:
                        moving.add(app)
                        q.append(app)

            else:
                for c in range(m):
                    app = board[0][c]

                    if app == 0 or board[n - 1][c] != app:
                        continue

                    split = False

                    for r in range(n):
                        if board[r][c] != app:
                            split = True
                            break

                    if split and app not in moving:
                        moving.add(app)
                        q.append(app)
            if not q:
                break

    for app, dir in commands:
        move(app, dir)

    return board
