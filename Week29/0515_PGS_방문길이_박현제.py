# 구현, O(len(dirs))
def solution(dirs):
    answer = 0
    n = 5
    d = {"U": [-1, 0], "D": [1, 0], "L" : [0, -1], "R" : [0, 1]}
    
    cx, cy = 0, 0
    
    visited = set()
    
    for i in dirs:
        dx, dy = d[i]

        nx, ny = cx + dx, cy + dy

        if -n <= nx <= n and -n <= ny <= n:            
            visited.add((cx, cy, nx, ny))
            visited.add((nx, ny, cx, cy))
            
            cx, cy = nx, ny
    
    answer = len(visited)//2
    
    return answer
