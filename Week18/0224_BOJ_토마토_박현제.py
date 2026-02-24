"""
알고리즘
- bfs - 최소 일수
- 1개 이상의 1 가능 -> 바로 큐에 넣기
- 모두 익었는지 확인하기 -> 초기에 cnt 세놓기
- 첫 날 -> 0일부터 시작

시간복잡도
-O(n^2)
"""
import sys
from collections import deque

# sys.stdin = open('input.txt', 'r')

input = sys.stdin.readline

m, n = map(int, input().split())

grid = [list(map(int, input().split())) for _ in range(n)]

ans = 0
cnt = 0

q = deque()
for i in range(n):
    for j in range(m):
        if grid[i][j] == 1:
            q.append((i, j))
        elif grid[i][j] == 0:
            cnt += 1

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

while q:
    q_size = len(q)

    for _ in range(q_size):
        cx, cy = q.popleft()

        for i in range(4):
            nx, ny = dx[i] + cx, dy[i] + cy

            if 0 <= nx < n and 0 <= ny < m and grid[nx][ny] == 0:
                grid[nx][ny] = 1
                cnt -= 1
                q.append((nx, ny))
    ans += 1


if cnt == 0:
    print(ans-1)
else:
    print(-1)

