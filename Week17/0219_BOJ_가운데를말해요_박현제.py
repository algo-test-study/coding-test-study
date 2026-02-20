"""
알고리즘
- 최소/ 최대 힙 둘 중 하나 삽입
- 순서가 잘못되면 스왑
- lh 출력
시간복잡도
-O(nlogn)


"""
import sys
import heapq

input = sys.stdin.readline # 안하면 시간초과

n = int(input())

lh = []
rh = []


ans = []

for _ in range(n):
	curr = int(input())

	if len(lh) == len(rh):
		heapq.heappush(lh, (-curr, curr))
	else:
		heapq.heappush(rh, (curr, curr))

	if rh and lh[0][1] > rh[0][0]:
		min_v = heapq.heappop(rh)[0]
		max_v = heapq.heappop(lh)[1]

		heapq.heappush(lh, (-min_v, min_v))
		heapq.heappush(rh, (max_v, max_v))

	ans.append(lh[0][1])

for i in ans:
	print(i)
