"""
알고리즘
- dfs

시간복잡도
-O(n^2)


"""
import sys
import heapq


# sys.stdin = open('input.txt', 'r')
sys.setrecursionlimit(100000)
input = sys.stdin.readline

n = int(input())
grid = []
max_len = 0

for _ in range(n):
    row = list(map(int, input().split()))
    grid.append(row)
    max_len = max(max_len, max(row))

ans = 0

def dfs(cx, cy, h, n, grid, visit):
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    for i in range(4):
        nx = cx + dx[i]
        ny = cy + dy[i]

        if 0 <= nx < n and 0 <= ny < n:
            if not visit[nx][ny] and grid[nx][ny] > h:
                visit[nx][ny] = True
                dfs(nx, ny, h, n, grid, visit)

for h in range(max_len + 1):
    visit = [[False] * n for _ in range(n)]
    cnt = 0

    for i in range(n):
        for j in range(n):
            if not visit[i][j] and grid[i][j] > h:
                visit[i][j] = True
                
                dfs(i, j, h, n, grid, visit)
                cnt += 1
    
    ans = max(ans, cnt)

print(ans)
