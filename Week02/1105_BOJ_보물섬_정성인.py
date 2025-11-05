from collections import deque

H, W = map(int, input().split())
graph = [list(input().strip()) for _ in range(H)]
dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]

def bfs(start_x, start_y):
    visited = [[False]*W for _ in range(H)]
    visited[start_y][start_x] = True
    answer = 0

    q = deque([(start_x, start_y, 0)])

    while q:
        x, y, dist = q.popleft()
        answer = max(0, dist)

        for k in range(4):
            nx, ny = x+dx[k], y+dy[k]

            if nx < 0 or nx >= W or ny < 0 or ny >= H:
                continue
            if visited[ny][nx] or graph[ny][nx] == 'W':
                continue
            q.append((nx, ny, dist+1))
            visited[ny][nx] = True
    return answer

answer = 0
for x in range(W):
    for y in range(H):
        if graph[y][x] == 'L':
            answer = max(answer, bfs(x, y))
print(answer)
