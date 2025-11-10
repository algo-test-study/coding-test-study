import sys
sys.setrecursionlimit(100000)
    
def solution(n, wires):
    answer = float('inf')
    graph = [[] for _ in range(n+1)]
    
    for x, y in wires:
        graph[x].append(y)
        graph[y].append(x)
    
    visited = [False]*(n+1)
    subtree = [-1] * (n+1)
    
    def dfs(node):
        visited[node] = True
        cnt = 1
        
        for nxt in graph[node]:
            if not visited[nxt]:
                cnt += dfs(nxt)
        subtree[node] = cnt
        return cnt
    dfs(1)
    
    for x, y in wires:
        child = x if subtree[x] < subtree[y] else y
        
        answer = min(answer, abs(subtree[child]-(n-subtree[child])))
    return answer
