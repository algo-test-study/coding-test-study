"""
level 최솟값 -> xxxxoooo

파라메트릭 서치
- O(log(max(diffs)) * n)
"""

def solution(diffs, times, limit):

    n = len(diffs)
    
    def is_valid(level):
        total_time = times[0]
        
        for i in range(1, n):
            time_cur, time_prev = times[i], times[i-1]
            diff = diffs[i]
            
            if diff <= level:
                total_time += time_cur
            else:
                total_time += (diff - level) * (time_cur + time_prev) + time_cur
                
        if total_time <= limit:
            return True
        return False
                
    s = 1
    e = max(diffs)
    
    while s <= e:
        mid = (s + e) // 2
       
        if is_valid(mid):
            e = mid - 1
            
        else:
            s = mid + 1
    
    return s
