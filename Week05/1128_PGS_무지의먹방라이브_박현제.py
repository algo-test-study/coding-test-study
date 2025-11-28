"""
답 참고함

알고리즘
- 완전 탐색 X: 2*10^5*10^8
- pq에 전체 음식 넣기
- 작은 음식부터 poll 
시간복잡도
- O(nlogn) = O(2×10^5*log(2*10^5))
"""
import heapq
def solution(food_times, k):
    if sum(food_times) <= k:
        return -1
    
    pq = []
    
    for i, v in enumerate(food_times):
        heapq.heappush(pq, (v, i+1))
    
    total = 0  # 지금까지 사용된 누적 시간
    prev = 0   # 이전 레벨 최소 시간
    cnt = len(food_times)

    while pq:
        min_time = pq[0][0]
        spend = (min_time - prev) * cnt

        if total + spend > k:
            break

        heapq.heappop(pq)
        total += spend
        prev = min_time
        cnt -= 1
    
    pq.sort(key=lambda x: x[1])
    ans = pq[(k - total) % cnt][1]
    return ans
