def solution(n, times):
    answer = 0
    
    
    s,e = 1, max(times) * n 
    
    def is_valid(curr_time):
        total = 0
        
        for time in times:
            total += curr_time // time
            if total >= n:
                return True
                
        return False
                
    while s <= e:
        mid = (s + e)//2
        
        if is_valid(mid):
            e = mid - 1
            answer = mid
        else:
            s = mid + 1
            

    return answer
