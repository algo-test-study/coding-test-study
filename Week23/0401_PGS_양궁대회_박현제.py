"""
백트래킹, O(11(10 + n)!/10!n!)
"""

def solution(n, info):
    answer = []
    
    a = info 
    b = [0]*11 #
    
    max_v = -1
    
    def get_score(b):
        a_value = 0
        b_value = 0
        for k in range(11):
            if a[k] == 0 and b[k] == 0:
                continue
            if a[k] < b[k]: #
                b_value += 10 - k
            else:
                a_value += 10 - k
        
        return b_value - a_value

    def dfs(at, depth):
        nonlocal answer, max_v
        
        if depth == n:
            score = get_score(b)
            
            if score > 0 and score > max_v:
                max_v = score
                answer = b.copy()
                
            return
    
        for i in range(at, -1, -1):
            
            b[i] += 1
            dfs(i, depth + 1)
            b[i] -= 1
    
    dfs(10, 0)
    
    return answer if answer else [-1]
