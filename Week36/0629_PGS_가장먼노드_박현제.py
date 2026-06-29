"""
- 그래프 만듦
- bfs
O(N+M)
"""
from collections import deque, defaultdict
def solution(n, edge):
    answer = 0
    
    g = defaultdict(list)
    
    for a, b in edge:
        g[a].append(b)
        g[b].append(a)
    
    q = deque()
    visited = [False] * (n+1)
    q.append((1, 1))
    visited[1] = True
    
    std = 0
    while q:
        curr, cnt = q.popleft()
        
        if std < cnt:
            std = cnt
            answer = 1
        else:
            answer += 1
            
        for nxt in g[curr]:
            if visited[nxt]:
                continue
            visited[nxt] = True
            q.append((nxt, cnt + 1))
            
    return answer
