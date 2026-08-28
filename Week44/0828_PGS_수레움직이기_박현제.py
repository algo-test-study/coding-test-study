def solution(maze):
    n = len(maze)
    m = len(maze[0])

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    red = blue = None

    for i in range(n):
        for j in range(m):
            if maze[i][j] == 1:
                red = (i, j)
            elif maze[i][j] == 2:
                blue = (i, j)

    visited_r = [[False] * m for _ in range(n)]    
    visited_b = [[False] * m for _ in range(n)]

    visited_r[red[0]][red[1]] = True
    visited_b[blue[0]][blue[1]] = True

    INF = float('inf')
    answer = INF

    def dfs(rx, ry, bx, by, depth):
        nonlocal answer

        if depth >= answer:
            return

        if maze[rx][ry] == 3 and maze[bx][by] == 4:
            answer = depth
            return

        red_finished = maze[rx][ry] == 3
        blue_finished = maze[bx][by] == 4
        red_moves = []

        if red_finished:

            red_moves.append((rx, ry))
        else:
            for d in range(4):
                nr = rx + dx[d]
                nc = ry + dy[d]

                if not (0 <= nr < n and 0 <= nc < m):
                    continue

                if maze[nr][nc] == 5:
                    continue

                if visited_r[nr][nc]:
                    continue

                red_moves.append((nr, nc))

        blue_moves = []

        if blue_finished:
            blue_moves.append((bx, by))
        else:
            for d in range(4):
                nr = bx + dx[d]
                nc = by + dy[d]

                if not (0 <= nr < n and 0 <= nc < m):
                    continue

                if maze[nr][nc] == 5:
                    continue

                if visited_b[nr][nc]:
                    continue

                blue_moves.append((nr, nc))

        for nrx, nry in red_moves:
            for nbx, nby in blue_moves:

                if nrx == nbx and nry == nby:
                    continue

                if nrx == bx and nry == by \
                        and nbx == rx and nby == ry:
                    continue

                red_new = not red_finished
                blue_new = not blue_finished

                if red_new:
                    visited_r[nrx][nry] = True

                if blue_new:
                    visited_b[nbx][nby] = True

                dfs(nrx, nry, nbx, nby, depth + 1)

                if red_new:
                    visited_r[nrx][nry] = False

                if blue_new:
                    visited_b[nbx][nby] = False

    dfs(red[0], red[1], blue[0], blue[1], 0)

    return answer if answer != INF else 0
