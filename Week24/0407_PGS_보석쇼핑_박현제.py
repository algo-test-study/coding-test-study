"""
투포인터, O(n)
"""
from collections import defaultdict

def solution(gems):
    
    n = len(gems)
    length = n-1
    answer = [1, n]
    
    d = defaultdict(int)
    
    full = len(set(gems))
    
    print(full)
    d[gems[0]] = 1
    
    s, e = 0, 0
    
    while s < n:
        if len(d) < full:
            if e < n-1:
                e+=1
                
                key = gems[e]
                d[key] += 1
              
            else:
                break;
        else:
            key = gems[s]
            d[key] -= 1
          
            if d[key] == 0:
                del d[key]
              
            if e - s < length:
                answer = [s + 1, e+1]
                length = e - s
            s+=1
                
            
    return answer
