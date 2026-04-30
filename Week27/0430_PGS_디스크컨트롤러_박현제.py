import heapq

def solution(jobs):
    ans = 0
    
    arr = []
    n = len(jobs)
    for i in range(n):
        s, l = jobs[i]
        arr.append((s, l, i))
    
    arr.sort()
    
    hq = []
    
    curr_time = 0
    idx = 0
    end = 0
    
    while end < n:
        while idx < n and arr[idx][0] <= curr_time:
            s, l, i = arr[idx]
            heapq.heappush(hq, (l, s, i))
            idx += 1
        
        if hq:
            time, req, _ = heapq.heappop(hq)
            curr_time += time
            ans += curr_time - req
            end += 1
        else:
            curr_time = arr[idx][0]
    ans //= n
    return ans
