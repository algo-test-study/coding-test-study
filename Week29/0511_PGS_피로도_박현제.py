# 완전 탐색, O(n!)
def solution(k, dungeons):
    answer = -1
    n = len(dungeons)
    visited = [False]*n
    def perm(depth, curr, cnt):
        nonlocal answer, n, dungeons, visited

        if depth >= n:
            return
        
        for i in range(n):
            if visited[i]:
                continue
            min_p, minus_p = dungeons[i]
            if min_p <= curr:
                visited[i] = True
                answer = max(answer, cnt + 1)
                perm(depth + 1, curr - minus_p, cnt + 1)
                visited[i] = False
                
    perm(0, k, 0)
    return answer
