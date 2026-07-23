"""
우선순위큐, O(nlogn)
"""
import heapq
def solution(jobs):
    answer = 0
    n = len(jobs)
    pq = []
    
    jobs.sort()
    
    total = jobs[0][1]
    prev_start = jobs[0][0]
    prev_end = jobs[0][0] + jobs[0][1]
    
    
    i = 1
    cnt = 1
    while(cnt < n):
        while i < n and jobs[i][0] <= prev_end:
            start, ran = jobs[i]
            heapq.heappush(pq, (ran, start)) # 사실상 작업 번호 정렬은 필요 없음
            i+=1

        if pq:    
            curr_ran, curr_start = heapq.heappop(pq)

            #if prev_end > curr_start:
            total += prev_end - curr_start + curr_ran
            prev_end += curr_ran
            cnt += 1
        else:
            prev_end = jobs[i][0]
          
    return total // n
