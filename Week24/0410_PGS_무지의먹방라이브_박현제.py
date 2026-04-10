"""
우선순위큐, O(nlogn)
"""
import heapq
def solution(food_times, k):
    if sum(food_times) <= k:
        return -1
    hq = []
    for i, time in enumerate(food_times):
        heapq.heappush(hq, (time, i+1))

    total_time = 0   
    prev = 0 
    cnt = len(food_times)

    while hq:
        min_time = hq[0][0]

        spend = (min_time - prev) * cnt

        if total_time + spend > k:
            break
            
        heapq.heappop(hq)
        total_time += spend
        prev = min_time
        cnt -= 1

    hq.sort(key=lambda x: x[1])
    
    i = (k - total_time) % cnt

    answer = hq[i][1]
    return answer
