"""
알고리즘
- 최소힙에 파일 크기 삽입
- 작은거 2개 ans에 더함
- 반복

시간복잡도
-O(tklogk)
"""
import sys
import heapq

input = sys.stdin.readline
t = int(input())

for _ in range(t):
    ans = 0

    n = int(input())
    pq = list(map(int, input().split())) 
    
    heapq.heapify(pq)

    while len(pq) > 1:

        a = heapq.heappop(pq)
        b = heapq.heappop(pq)

        curr = a + b
        ans += curr
        heapq.heappush(pq, curr)
    print(ans)




