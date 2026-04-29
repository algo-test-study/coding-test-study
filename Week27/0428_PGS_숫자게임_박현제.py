"""
정렬, 그리디 / O(N)

"""
def solution(A, B):
    ans = 0
    A.sort()
    B.sort()
    
    n = len(A)
    a = 0
    for b in range(n):
        if A[a] < B[b]:
            a += 1
            ans += 1
    return ans
        
            
        
