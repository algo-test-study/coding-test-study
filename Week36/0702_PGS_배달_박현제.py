"""
1~n의 번호
양방향
가중치는 항상 양수
시작점은 1로 고정
최단 경로

a: 가중치 있을 때, 특점 점에서 다른 점까지의 최단 경로 -> 다익스트라

1. 다익스트라
2. dist <= k 인 곳만 ans++

t: O(m + mlogm + n) = O(mlogm), m = len(road)

"""
import heapq
from collections import defaultdict
def solution(N, road, K):
    answer = 0
    
    pq = []
    
    g = defaultdict(list)
    
    for x, y, w in road:
        g[x].append((y, w))
        g[y].append((x, w))
        
    
    INF = float('inf')
   
    dist = [INF]*(N+1)
    dist[1] = 0
    heapq.heappush(pq, (0, 1))
    
    while pq:
        _, curr = heapq.heappop(pq)
        
        for nxt, nw in g[curr]:
            if dist[curr] + nw < dist[nxt]:
                dist[nxt] = dist[curr] + nw            
                heapq.heappush(pq, (dist[nxt], nxt))
                
    for i in range(1, N+1):
        if dist[i] <= K:
            answer += 1
    
    return answer
