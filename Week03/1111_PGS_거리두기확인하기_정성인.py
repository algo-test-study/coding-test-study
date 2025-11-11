from collections import deque

def is_available(start_x, start_y, nx, ny, places):
    if ny == start_y or nx == start_x:
        mid_x, mid_y = (start_x+nx)//2, (start_y+ny)//2
        
        if places[mid_y][mid_x] == 'X':
            return True
    else:
        if places[start_y][nx] == 'X' and places[ny][start_x] == 'X':
            return True
    return False
                
def bfs(start_x, start_y, places):
    dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]
    W, H = len(places[0]), len(places)
    visited = [[False]*W for _ in range(H)]
    visited[start_y][start_x] = True

    q = deque([(start_x, start_y, 0)])

    while q:
        x, y, dist = q.popleft()
        if dist >= 2:
            continue

        for k in range(4):
            nx, ny = x+dx[k], y+dy[k]

            if nx < 0 or nx >= W or ny < 0 or ny >= H or visited[ny][nx] == True:
                continue
            
            cell = places[ny][nx]
            
            if cell != 'P':
                q.append((nx, ny, dist+1))
                visited[ny][nx] = True
                continue
            
            if dist == 0:
                return False
            
            if not is_available(start_x, start_y, nx, ny, places):
                return False
            
            q.append((nx, ny, dist+1))
            visited[ny][nx] = True
    return True
    
def solution(places):
    answer = []
    
    for i, place in enumerate(places):
        ok = 1
        for y in range(len(place)):
            for x in range(len(place[0])):
                if place[y][x] == 'P' and not bfs(x, y, place):
                    ok = 0
                    break
            if ok == 0:
                break
        answer.append(ok)

    return answer
