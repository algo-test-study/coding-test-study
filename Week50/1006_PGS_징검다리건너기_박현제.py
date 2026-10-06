import heapq
def solution(stones, k):

    def is_valid(mid):
        cnt = 0
        for stone in stones:
            if stone - (mid-1) <= 0:
                cnt+=1
            else:
                cnt = 0
            if cnt >= k:
                return False
        return True
    answer = 0  
    
    s = 1
    e = max(stones)
    
    while s <= e:
        mid = (s + e) // 2
        
        if is_valid(mid):
            answer = max(answer, mid)
            s = mid + 1
        else:
            e = mid - 1
    
    return answer
