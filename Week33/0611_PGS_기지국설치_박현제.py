"""
?: 증설할 기지국 개수의 최솟값
O(len(stations))
"""
import math
def solution(n, stations, w):
    answer = 0
    s = 1
    
    mod = 2*w + 1
    
    for station in stations:
        e = station - w
        diff = e - s
        
        if diff > 0:
            answer += math.ceil(diff / mod)
        
        s = station + w + 1
    
    
    diff = n + 1 - s
    if diff >= 0:
        answer += math.ceil( diff / mod)
    
    return answer
  
