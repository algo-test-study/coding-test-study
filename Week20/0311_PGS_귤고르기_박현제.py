from collections import defaultdict
import heapq

def solution(k, tangerine):
    answer = 0
    
    d = defaultdict(int)
    
    pq = []
    
    for size in tangerine:
        d[size] += 1
        
    for size, cnt in d.items():
        heapq.heappush(pq, (-cnt, size))

    while pq:
        cnt, size = heapq.heappop(pq)
        k -= -cnt
        answer += 1
        
        if k <= 0:
            break
    return answer
