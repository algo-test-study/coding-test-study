"""
시뮬, O(nm)
"""

import sys
from collections import deque


input = sys.stdin.readline
ans = 0
n, m = map(int, input().split())
sx, sy, d = map(int, input().split())

arr = [list(map(int, input().split())) for i in range(n)]

dx = [-1,0,1,0]
dy = [0,1,0,-1]

def isOutOfBound(nx, ny):
	return 0 <= nx <= n and 0 <= ny <= m

def dfs(cx, cy):
	global ans, d

	if arr[cx][cy] == 0:
		arr[cx][cy] = -1
		ans += 1

	is_move = False
	
	for i in range(4):
		nx, ny = cx + dx[i], cy + dy[i]
		if isOutOfBound(nx, ny) and arr[nx][ny] == 0:
			is_move = True

	if is_move:
		d = (d + 3)%4
		nx, ny = cx + dx[d], cy + dy[d]
		if isOutOfBound(nx, ny) and arr[nx][ny] == 0:
			dfs(nx, ny)
			return
		dfs(cx, cy)
				
	else:
		back = (d + 2)%4
		nx, ny = cx + dx[back], cy + dy[back]
		if isOutOfBound(nx, ny) and arr[nx][ny] != 1:
			dfs(nx, ny)

dfs(sx, sy)


print(ans)
