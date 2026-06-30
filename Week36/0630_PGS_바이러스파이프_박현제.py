"""
- a, b, c 파이프
- 파이프는 최대 k 번 열고 닫기 가능


?: 감염 배양체 개수의 최댓값

a:
1. 순열로 파이프 ABC 순서 선택 - 3*2^(k-1) = 2^k = 2^10 (항상 바로 이전과 다른 파이프를 열었다 닫는 것만이 의미가 있음)
2. 전파 -> 멀티노드 bfs - O(n+m)

t: O((n+m)*3*2^k)

"""
from collections import defaultdict, deque
def solution(n, infection, edges, k):
    ans = 0
    g = defaultdict(list)
    
    for x, y, t in edges:
        
        g[x].append((y, t))
        g[y].append((x, t))
        
    def infect(pipe, visited):
        q = deque(visited)
        
        while q:
            curr = q.popleft()
            
            for nxt, typ in g[curr]:
                if nxt not in visited and pipe == typ:
                    visited.add(nxt)
                    q.append(nxt)
    
    def get_pipe_order(prev_idx, depth, curr_order):
        nonlocal ans
        if depth == k:
            visited = set()
            visited.add(infection)
            
            for pipe in curr_order:
                infect(pipe, visited)
            ans = max(ans, len(visited))
            return
        
        for idx in range(1, 4):
            if prev_idx == -1 or prev_idx != idx:
                curr_order.append(idx)
                get_pipe_order(idx, depth + 1, curr_order)
                curr_order.pop()
    
        
    get_pipe_order(-1, 0, [])    
            
    
    return ans
