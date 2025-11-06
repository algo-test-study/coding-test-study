def solution(A, B):
    answer = 0
    B.sort()
    A.sort()
    i, j = 0, 0
    
    while i < len(A) and j < len(B):
        if A[i] < B[j]:
            answer += 1
            i += 1
            j += 1
        else:
            j += 1
    return answer
