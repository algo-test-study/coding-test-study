"""
1. 완탐
O(n^2) -> 불가능

2. set
이전 값을 set에 넣어버리고 현재 값은 잘라서 겹치는 게 하나라도 있으면 false
O(n * l^2)
"""

def solution(phone_book): 
    s = set(phone_book)
    
    n = len(phone_book)
    
    for i in range(n):
        curr = phone_book[i]
        for j in range(1, len(curr)):
            if curr[0:j] in s:
                return False
    return True
