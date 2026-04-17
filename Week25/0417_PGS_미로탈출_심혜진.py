from collections import deque


def solution(maps):
    num_rows = len(maps)
    num_cols = len(maps[0])

    dr = [-1, 1, 0, 0]
    dc = [0, 0, -1, 1]

    START = "S"
    LEVER = "L"
    EXIT = "E"
    WALL = "X"

    def is_valid(r, c):
        return 0 <= r < num_rows and 0 <= c < num_cols

    def get_distance(start_char, end_char):
        queue = deque()
        visited = [[False] * num_cols for _ in range(num_rows)]

        for i in range(num_rows):
            for j in range(num_cols):
                if maps[i][j] == start_char:
                    queue.append((i, j, 0))
                    visited[i][j] = True

        while queue:
            r, c, dist = queue.popleft()

            if maps[r][c] == end_char:
                return dist

            for d in range(4):
                nr = r + dr[d]
                nc = c + dc[d]

                if not is_valid(nr, nc):
                    continue
                if visited[nr][nc]:
                    continue
                if maps[nr][nc] == WALL:
                    continue

                visited[nr][nc] = True
                queue.append((nr, nc, dist + 1))

        return -1

    start_to_lever = get_distance(START, LEVER)
    lever_to_exit = get_distance(LEVER, EXIT)

    if start_to_lever == -1 or lever_to_exit == -1:
        return -1

    return start_to_lever + lever_to_exit
