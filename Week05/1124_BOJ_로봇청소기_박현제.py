# 시뮬레이션
# 알고리즘
# - 재귀
# - 주변 4칸 청소되었는지 확인
# - 분기처리
# 시간복잡도
# - O(n*m)
n, m = map(int, input().split())

sx, sy, sd = map(int, input().split())

dx = [-1,0,1,0]
dy = [0,1,0,-1]

area = []

for i in range(n):
	lst = list(map(int, input().split()))
	area.append(lst)

visited = [[False]*m for _ in range(n)]

ans = 1
visited[sx][sy] = True

def isOutOfBounds(nx, ny):
	return nx >= n or ny >= m or nx < 0 or ny < 0

def dfs(cx, cy, cd):
	global ans
	cnt = 0

	for i in range(4):
		nx = cx + dx[i]
		ny = cy + dy[i]

		if isOutOfBounds(nx, ny) or area[nx][ny] == 1 or visited[nx][ny]: continue
		cnt+=1

	if cnt == 0:
		nd = (cd+2)%4 
		nx = cx + dx[nd]
		ny = cy + dy[nd]
		
		if isOutOfBounds(nx, ny) or area[nx][ny] == 1: return
		dfs(nx, ny, cd)
	else:
		nd = (cd+3)%4
		nx = cx + dx[nd]
		ny = cy + dy[nd]

		if isOutOfBounds(nx, ny) or area[nx][ny] == 1 or visited[nx][ny]:
			dfs(cx, cy, nd)
		else:
			ans+=1
			visited[nx][ny] = True
			dfs(nx, ny, nd)

dfs(sx, sy, sd)
print(ans)
