# 정렬, O(nlogn)
def solution(citations):
    answer = 0
    
    citations.sort()
    
    n = len(citations)
    for i in range(n-1, -1, -1):
        h = citations[i]
        if n - i >= h:
            print(h)
            answer = h
            break
    
    return answer
