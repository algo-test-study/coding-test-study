"""
O(n)
"""
from collections import Counter
def solution(clothes):
    answer = 1
    
    kinds = [kind for _, kind in clothes]
    c = Counter(kinds)
    
    for v in c.values():
        answer *= (v + 1)
    
    return answer - 1
