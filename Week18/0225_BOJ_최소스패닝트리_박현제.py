"""
알고리즘
- MST
- 프림 - pq. 최소 간선 택
시간복잡도
-O(v +e)


"""
import sys
import heapq


# sys.stdin = open('input.txt', 'r')

input = sys.stdin.readline
v, e = map(int, input().split())

g = {}

for i in range(1, v + 1):
    g[i] = []

for i in range(e):
    a, b, c = map(int, input().split())

    g[a].append([b, c])
    g[b].append([a, c])

ans = 0
q = []

visit = [False] * (v + 1)

visit[1] = True

for nexts in g[1]:
    heapq.heappush(q, [nexts[1], nexts[0]])

while q:
    w, cur = heapq.heappop(q)

    if visit[cur]:
        continue
    visit[cur] = True

    ans += w

    for nexts in g[cur]:
        nxt = nexts[0]
        nw = nexts[1]

        if visit[nxt]:
            continue
        heapq.heappush(q, [nw, nxt])

print(ans)
