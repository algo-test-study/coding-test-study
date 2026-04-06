"""
시뮬, O(nm)
"""
import sys
from collections import deque


input = sys.stdin.readline
ans = 0
n, m = map(int, input().split())
cx, cy, d = map(int, input().split())

arr = [list(map(int, input().split())) for i in range(n)]

dx = [-1,0,1,0]
dy = [0,1,0,-1]

def is_out_of_bounds(nx, ny):
	return 0 <= nx <= n and 0 <= ny <= m


while True:

	if arr[cx][cy] == 0:
		arr[cx][cy] = -1
		ans += 1

	is_move = False
	
	for i in range(4):
		nx, ny = cx + dx[i], cy + dy[i]
		if is_out_of_bounds(nx, ny) and arr[nx][ny] == 0:
			is_move = True

	if is_move:
		d = (d + 3)%4
		nx, ny = cx + dx[d], cy + dy[d]
		if is_out_of_bounds(nx, ny) and arr[nx][ny] == 0:
			cx, cy = nx, ny
				
	else:
		back = (d + 2)%4
		nx, ny = cx + dx[back], cy + dy[back]
		if is_out_of_bounds(nx, ny) and arr[nx][ny] != 1:
			cx, cy = nx, ny
		else:
			break


print(ans)
