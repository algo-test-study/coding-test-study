"""
최소 필요 피로도 -> 제한
소모 피로도 -> -

알고리즘
- 던전 개수 8 -> 완탐 가능
- 순열로 전체 탐색 / if 남은 피로도 < 최소 필요 피로도 -> 종료

시간복잡도
- O(n!)
"""
def solution(k, dungeons):
    answer = 0
    n = len(dungeons)
    visited = [False] * n
    
    def dfs(curr, depth):
        nonlocal answer
        answer = max(answer, depth)
        for i in range(n):
            if visited[i] or dungeons[i][0] > curr : continue
            visited[i] = True
            dfs(curr - dungeons[i][1], depth+1)
            visited[i] = False
        
    dfs(k, 0)
    
    return answer
