"""
그리디, O(nlogn)
"""
def solution(A, B):
    answer = 0
    
    A.sort()
    B.sort()
    
    n = len(A)
    
    i = 0
    for b in B:

        a = A[i]
        if a < b:
            answer += 1
            i += 1
            
    return answer
