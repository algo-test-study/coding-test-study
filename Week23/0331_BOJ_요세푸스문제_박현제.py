"""
큐, O(nk)
"""

import sys
from collections import deque

input = sys.stdin.readline

n, k = map(int, input().split())


arr = [i for i in range(1, n+1)]
q = deque(arr)

ans = []

while q:
	# for _ in range(k-1):
	# 	q.append(q.popleft())
	q.rotate(-(k-1))
	ans.append(q.popleft())


print('<' + ', '.join(map(str, ans)) + ">")
