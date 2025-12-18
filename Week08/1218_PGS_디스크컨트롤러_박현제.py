"""
우선순위 높은 작업 먼저 처리할 때 요청 반환 시간 평균
우선순위: 소요 시간 > 요청 시간 > 작업 번호
알고리즘
- 요청 시간으로 정렬
- 소요 시간으로 우선순위 큐 (작업, 요청)
- 요청 시간 <= 현재 시간 -> add

시간복잡도
- O(nlogn)
"""
import heapq

def solution(jobs):
    answer = 0

    jobs.sort(key=lambda x : x[0])

    q = []
    cnt = 0
    idx = 0
    time = 0
    n = len(jobs)

    while q or cnt < n:
        while idx < n and jobs[idx][0] <= time:
            req, du = jobs[idx]
            heapq.heappush(q, (du, req))
            idx += 1
        if not q:
            time = jobs[idx][0]
        else:
            du, req = heapq.heappop(q)
            time += du
            answer += time - req
            cnt += 1

    return answer//n
