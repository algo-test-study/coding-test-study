"""
O(nlogn)
"""
def solution(routes):
    ans = 0
    
    routes.sort(key=lambda x: (x[1], x[0]))
    
    n = len(routes)
    
    if n == 1:
        return 1
    
    prev_end = routes[0][1]
    i = 1
    
    while i < n:
        while i < n and prev_end >= routes[i][0]:
            i += 1
            
        ans += 1
        
        if i < n:
            prev_end = routes[i][1]
        
    return ans
