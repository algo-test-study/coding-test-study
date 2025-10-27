from collections import deque

def bfs(start, visited, computers):
    q = deque([start])
    visited[start] = True
    
    while q:
        x = q.popleft()
        
        for nxt, connected in enumerate(computers[x]):
            if connected and not visited[nxt]:
                visited[nxt] = True
                q.append(nxt)
    
def solution(n, computers):
    answer = 0
    visited = [False]*n
    
    for i in range(n):
        if not visited[i]:
            bfs(i, visited, computers)
            answer += 1
    return answer
