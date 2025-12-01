from collections import deque

def solution(n, edge):
    answer = 0
    visited = [-1] * (n+1)
    _maps = [[] for _ in range(n+1)]
    
    for a, b in edge:
        _maps[a].append(b)
        _maps[b].append(a)
    
    visited[1] = 0
    q = deque([1])
    
    while q:
        x = q.popleft()
        for nx in _maps[x]:
            if visited[nx] == -1:
                visited[nx] = visited[x]+1
                q.append(nx)
    
    return visited.count(max(visited))