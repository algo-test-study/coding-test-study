"""
bfs, O(O(n^2 * l))
"""
from collections import deque
def solution(begin, target, words):
    n = len(words)
    visited = [False] * n

    def is_valid(curr, nxt):
        cnt = 0
        
        for i in range(len(nxt)):
            if curr[i] != nxt[i]:
                cnt += 1
            if cnt > 1: 
                return False
            
        if cnt == 1:
            return True
        
        return False
    
    q = deque()    
    q.append((begin, 0))

    while q:
        curr, cnt = q.popleft()
        
        if curr == target:

            return cnt
        
        for i in range(n):
            nxt = words[i]
            
            if visited[i]:
                continue
                
            if not is_valid(curr, nxt):
                continue
                
            visited[i] = True
            q.append((nxt, cnt + 1))

    
    return 0
