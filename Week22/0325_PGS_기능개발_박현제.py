"""
구현, O(n)

"""
import math
def solution(progresses, speeds):
    ans = []
    
    time = 0
    cnt = 0
    for p, s in zip(progresses, speeds):
        
        curr_time = math.ceil((100 - p)/s)
            
        if curr_time <= time:
            cnt += 1
        else:
            time = curr_time
            if cnt > 0:
                ans.append(cnt)
            cnt = 1
            
    ans.append(cnt)
        
    return ans
