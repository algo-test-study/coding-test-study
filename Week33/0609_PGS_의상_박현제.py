"""
0-의상 개수 * ... - 1

의상 종류: 개수

해시, O(n)
"""
from collections import Counter
def solution(clothes):
    answer = 1
    
    c = Counter([kind for _, kind in clothes])
    
    for v in c.values():
        answer *= (v + 1)
    
    return answer - 1
