# 파라메트릭 서치, O(len(times)log t)
def solution(n, times):
    answer = 0
    
    t = max(times) * n
    
    s, e = 1, t
    
    def is_valid(target):
        nonlocal times, n

        total = 0
        for time in times:
            total += target // time
            
            if total >= n:
                return True
        return False
        
    
    while s <= e:
        mid = (s + e) // 2
        
        if is_valid(mid):
            answer = mid
            e = mid - 1
        else:
            s = mid + 1

    return answer
