"""

최댓값 -> 우선순위 큐  / 사실 그냥 정렬 후 인덱스가 더 나을지도
최댓값 <= 현재 -> 빼기
정렬, 큐, O(nlogn)
"""

from collections import deque
def solution(priorities, location):
    answer = 0
    q = deque()
    n = len(priorities)
    
    for i in range(n):
        q.append((i, priorities[i]))
    priorities.sort(reverse=True)
    
    curr = 0
    
    while q:
        i, p = q.popleft()
        
        if priorities[curr] == p:
            curr +=1
            if location == i:
                return curr   
        else:
            q.append((i, p))
        
    return curr
