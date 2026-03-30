"""
다익스트라, e = len(road) - O(eloge)
"""

import heapq
from collections import defaultdict

def solution(N, road, K):
    answer = 0
    
    g = defaultdict(list)

    for a, b, w in road:
        g[a].append((b, w))
        g[b].append((a, w))

    INF = float('inf')
    
    dist = [INF]*(N + 1)
    
    pq = []

    heapq.heappush(pq, (1, 0))
    dist[1] = 0
   
    while pq:
        curr, _ = heapq.heappop(pq)
        
        for nxt, nw in g[curr]: 
            if dist[nxt] > dist[curr] + nw:
                dist[nxt] = dist[curr] + nw
                heapq.heappush(pq, (nxt, dist[nxt]))

    for i in range(1, N + 1):
        if dist[i] <= K:
            answer += 1 
            
    print(dist)
    return answer
