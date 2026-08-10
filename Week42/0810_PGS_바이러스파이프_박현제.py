
"""
O(2*k * nk)
"""
from collections import defaultdict, deque
def solution(n, infection, edges, k):
    g = defaultdict(list)
    
    for a, b, typ in edges:
        g[a].append((b, typ))
        g[b].append((a, typ))
    
    orders = []
    arr = []
    
    def perm(curr_typ, depth):
        
        if depth == k:
            orders.append(arr[:])
            return 
        
        for nxt_typ in range(1, 4):
            if nxt_typ == curr_typ:
                continue
            
            arr.append(nxt_typ)
            perm(nxt_typ, depth + 1)
            arr.pop()
    
    perm(-1, 0)

    def bfs(pipe):
        q = deque(visited)
        
        while q:
            curr = q.popleft()
            
            for nxt, typ in g[curr]:
                if nxt not in visited and pipe == typ:
                    visited.add(nxt)
                    q.append(nxt)
    max_v = 0

    for order in orders:
        visited = set()
        visited.add(infection)
        for pipe in order:
            bfs(pipe)
        max_v = max(max_v, len(visited))
        
    return max_v