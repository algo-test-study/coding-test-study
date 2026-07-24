"""
최소힙, 최대힙

삭제할때 deleted에 표시 - 인덱스

O(nlogn)
"""
import heapq
def solution(operations):
    answer = []
    
    min_q = []
    max_q = []
    
    deleted = [False] * len(operations)
    size = 0
    
    for i, op in enumerate(operations):
        cmd, val = op.split()
        val = int(val)

        if cmd == 'I':
            heapq.heappush(min_q, (val, i))
            heapq.heappush(max_q, (-val, i))
            size += 1
        else:
            if size == 0:
                continue
            if val == 1:
                while deleted[max_q[0][1]]:
                    heapq.heappop(max_q)
                _, idx = heapq.heappop(max_q)
            else:
                while deleted[min_q[0][1]]:
                    heapq.heappop(min_q)
                _, idx = heapq.heappop(min_q)
            deleted[idx] = True
            size -= 1

    if size == 0:
        return [0, 0]
    
    while deleted[min_q[0][1]]:
        heapq.heappop(min_q)
        
    while deleted[max_q[0][1]]:
        heapq.heappop(max_q)
        
    answer = [-max_q[0][0], min_q[0][0]]
    
    return answer
