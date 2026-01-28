"""
알고리즘
- 최소 강의실 개수

일단 시작 시간으로 정렬
- 시작 -> 종료 순 정렬
- pq에 종료 시간 넣는다
- 가장 빠른 종료 <= 현 종료 -> 강의실 이용 종료. 강의실 비운다

시간복잡도
-O(nlgon)


"""
import heapq
n = int(input())

lst = [list(map(int, input().split())) for _ in range(n)]

lst.sort()

pq = []

heapq.heappush(pq, lst[0][1])

for i in range(1, n):
	if pq[0] <= lst[i][0]:
		heapq.heappop(pq)
	heapq.heappush(pq, lst[i][1])

print(len(pq))
