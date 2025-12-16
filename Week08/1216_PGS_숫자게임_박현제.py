"""
더 크면 승점

알고리즘
- a팀, b팀 정렬
- a < b -> i++, j++
- a > b -> j++
- a == b -> j++

시간 복잡도
- O(n)
"""

def solution(A, B):
    answer = 0
    n = len(A)
    
    A.sort()
    B.sort()
    
    i = 0
    j = 0
    
    while j < n:
        if A[i] < B[j]:
            i += 1
            answer += 1
        j += 1
            
    return answer
