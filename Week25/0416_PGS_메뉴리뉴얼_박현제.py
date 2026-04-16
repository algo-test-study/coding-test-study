"""
완탐, 구현, O(n*m*lCr)

"""
from itertools import combinations
from collections import Counter
def solution(orders, course):
    answer = []
    
    for r in course:
        c = Counter()
        
        for order in orders:
            order = sorted(order)

            for combo in combinations(order, r):
                c[combo] += 1
        
        if not c:
            continue
    
    
        max_cnt = max(c.values())

        if max_cnt >= 2:
            for curr, cnt in c.items():
                if cnt == max_cnt:
                    answer.append(''.join(curr))
    
    return sorted(answer)
