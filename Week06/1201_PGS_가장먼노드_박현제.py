"""
알고리즘
- 최단 경로 -> bfs
- 마지막 큐 size == 가장 멀리 떨어진 노드 개수

시간복잡도
- O(N+M)
"""
from collections import deque, defaultdict
def solution(n, edge):
    g = defaultdict(list)
    
    for a, b in edge:
        g[a].append(b)
        g[b].append(a)
        
    visited = [False]*(n+1)
    visited[1] = True
    
    q = deque()
    q.append(1)
    
    cnt = 0
    while q:
        q_size = len(q)
        for _ in range(q_size):      
            curr = q.popleft()
            for next in g[curr]:
                if visited[next]: continue
                visited[next] = True
                q.append(next)
        cnt = q_size          
    
    return cnt
