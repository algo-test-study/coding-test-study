"""
알고리즘
- bfs 최단 거리 문제

시간복잡도
- O(nm)

"""
from collections import deque
def solution(maps):
    
    dx = [-1,1,0,0]
    dy = [0,0,-1,1]
    
    n = len(maps)
    m = len(maps[0])
    
    q = deque()
    q.append([0, 0])
    maps[0][0] = 0
    dist = 1
    
    while q:
        q_size = len(q)
        
        for _ in range(q_size):
            cx, cy = q.popleft()
            if cx == n-1 and cy == m-1:
                return dist
            for i in range(4):
                nx = cx + dx[i]
                ny = cy + dy[i]
                if 0 <= nx < n and 0 <= ny < m and maps[nx][ny] == 1:
                    maps[nx][ny] = 0
                    q.append([nx, ny])
        dist += 1
        
    return -1
