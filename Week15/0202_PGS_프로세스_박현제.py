"""
알고리즘
- 가장 큰 우선순위 나올 때까지 빼고 뒤에거랑 비교
시간복잡도
- O(N^2)

"""
from collections import deque
def solution(priorities, location):
    answer = 0
    lst = [(p, i) for i, p in enumerate(priorities)]
    q = deque(lst)

    
    while q:
        curr = q.popleft()
        
        if q and max(t[0] for t in q) > curr[0]:
            q.append(curr)
        else:
            answer += 1
            if curr[1] == location:
                return answer

    return answer
