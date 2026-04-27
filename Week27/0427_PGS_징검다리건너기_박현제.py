"""
파라메트릭 서치, O(2*10^5log2*10^8)
"""
def solution(stones, k):
    answer = 0
    
    s = 1
    e = max(stones)

    def is_valid(target):
        nonlocal stones, k
        cnt = 0
        for stone in stones:
            if stone - target >= 0:
                cnt = 0
            else:
                cnt += 1
                
            if cnt >= k:
                return False
        return True
         
    
    while s <= e:
        mid = (s + e) // 2
        
        if is_valid(mid):
            answer = mid
            s = mid + 1
        else:
            e = mid - 1
    return answer
