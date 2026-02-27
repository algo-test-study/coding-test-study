"""
알고리즘
- as-is: bfs -> 유니온파인드...
- MST - 프림
시간복잡도
- O(n^2logn)
"""

import heapq

def solution(land, height):
    n = len(land)
    visited = [[False] * n for _ in range(n)]
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    pq = [(0, 0, 0)]
    answer = 0
    picked = 0
    target = n*n

    while picked < target:
        cost, x, y = heapq.heappop(pq)
        if visited[x][y]:
            continue

        visited[x][y] = True
        answer += cost
        picked += 1

        curr_h = land[x][y]
        
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            
            if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny]:
                
                diff = abs(land[nx][ny] - curr_h)
                if diff <= height:
                    cost = 0
                else:
                    cost = diff
                    
                heapq.heappush(pq, (cost, nx, ny))

    return answer
