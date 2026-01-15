"""
알고리즘
- 최소 변환 -> bfs
- 다음 -> 한 단어만 바뀐것 확인 후 연결
- 이미 방문 한 단어는 방문처리
시간복잡도
- O(n*n*word_n)
"""
from collections import deque
def solution(begin, target, words):
    answer = 0
    
    n = len(words)
    l = len(words[0])
    
    visited = [False]*n
    
    q = deque()
    q.append(begin)
    
    while q:
        q_size = len(q)
        for _ in range(q_size):
            curr = q.popleft()

            if curr == target:
                return answer
            
            for i in range(n):
                next = words[i]
                
                def is_valid():
                    curr_lst = list(curr)
                    next_lst = list(next)
                    
                    cnt = 0
                    
                    for i in range(l):
                        if curr_lst[i] == next_lst[i]:
                            cnt += 1
                    if cnt == l - 1:
                        return True
                    return False
                
                if not visited[i] and is_valid():
                    visited[i] = True
                    q.append(words[i])
        answer += 1
    
    return 0
