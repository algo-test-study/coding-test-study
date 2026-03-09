"""
알고리즘
- dfs

시간복잡도
- O(n*n)

Check Point
- dfs 내 c이 1일 때만 다음으로
- 처음에 c[i][i] = 1 이므로 예외처리 ->  nxt != curr 조건을 주었으나 어차피 visited 처리가 이전에 되므로 없어도 된다
"""
def solution(n, computers):
    answer = 0

    visited = [False] * n
    
    def dfs(curr):
        visited[curr] = True
        
        for nxt in range(n):
            if computers[curr][nxt] == 1 and not visited[nxt]:
                dfs(nxt)
    
    for i in range(n):
        if not visited[i]:
            dfs(i)
            answer += 1
    
    return answer
