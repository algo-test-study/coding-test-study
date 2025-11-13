import heapq
import sys
input = sys.stdin.readline

N = int(input())
meetings = [list(map(int, input().split())) for _ in range(N)]

meetings.sort()

hq = []

for start, end in meetings:
    if hq and hq[0] <= start:
        heapq.heappop(hq)
    heapq.heappush(hq, end)

print(len(hq))
