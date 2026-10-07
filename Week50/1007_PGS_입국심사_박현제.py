def solution(n, times):
    min_time = min(times)
    s, e = 1, min_time * n
    
    def is_valid(target):
        people = 0
        for time in times:
            people += target // time
            if people >= n:
                return True
        return False
    
    while s <= e:
        mid = (s + e) // 2
        if is_valid(mid):
            e = mid - 1
        else:
            s = mid + 1
   
    return s
