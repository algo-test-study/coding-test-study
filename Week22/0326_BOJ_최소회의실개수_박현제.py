"""
우선순위큐, O(nlogn)
"""

import sys
import heapq
input = sys.stdin.readline

# sys.stdin = open('input.txt', 'r')  # 삭제

n = int(input())

arr = []

for i in range(n):
	arr.append(tuple((map(int, input().strip().split()))))

arr.sort()

hq = []
ans = 0
for start, end in arr:
	while hq and hq[0] <= start:
		heapq.heappop(hq)

	heapq.heappush(hq,end)
	ans = max(ans,len(hq))


print(ans)
