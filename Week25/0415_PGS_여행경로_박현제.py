
"""
dfs, O(nlogn)
"""
from collections import defaultdict
def solution(tickets):
    n = len(tickets)
    tickets.sort()
    visited = [False] * n
    
    ans = []
    is_end = False

    def dfs(curr, depth, arr):
        nonlocal n, visited, ans, is_end
        
        if depth == n:
            ans.append(arr[:])
            is_end = True
            return
        
        for i, nxt in enumerate(tickets):
            s, e = nxt
            
            if visited[i] or curr != s:
                continue
                
            visited[i] = True
            arr.append(e)
            if not is_end:
                dfs(e, depth +1, arr)
            visited[i] = False
            arr.pop()
        
    dfs("ICN", 0, ["ICN"])
        
    return ans[0]
