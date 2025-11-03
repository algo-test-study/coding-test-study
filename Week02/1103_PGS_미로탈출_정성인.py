from collections import deque

def get_start(maps):
    for y in range(len(maps)):
        for x in range(len(maps[0])):
            if maps[y][x] == 'S':
                return [x, y]
    return [-1, -1]
def solution(maps):
    W, H = len(maps[0]), len(maps)
    visited = [[[False]*2 for _ in range(W)] for _ in range(H)]
    dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]
    
    start = get_start(maps)
    q = deque([(start[0], start[1], False, 0)])
    visited[start[1]][start[0]][False] = False
    
    while q:
        x, y, lever, t = q.popleft()
        
        if maps[y][x] == 'E' and lever:
            return t
        
        for k in range(4):
            nx, ny = x+dx[k], y+dy[k]
            
            if nx < 0 or nx >= W or ny < 0 or ny >= H:
                continue
            cell, nlever = maps[ny][nx], lever
            
            if cell == 'L' and not lever:
                nlever = True
            if visited[ny][nx][nlever] or maps[ny][nx] == 'X':
                continue
            
            visited[ny][nx][nlever] = True
            q.append((nx, ny, nlever, t+1))
    return -1
