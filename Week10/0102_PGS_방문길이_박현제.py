"""
알고리즘
- 길 방문 처리
- 방문 안되었을 경우만 ans++
시간복잡도
- O(len(dirs))
"""
def solution(dirs):
    answer = 0
    
    cx, cy = 0, 0
    dx = [0, 0, 1, -1]
    dy = [1, -1, 0, 0]
    
    dir_map = {
        'U': 0,
        'D': 1,
        'R': 2,
        'L': 3
    }
    visited = set()
    
    for d in dirs:
        i = dir_map[d]
        nx = cx + dx[i]
        ny = cy + dy[i]
        
        if -5 <= nx <= 5 and -5 <= ny <= 5:
            forw = ((cx, cy), (nx, ny))
            back = ((nx, ny), (cx, cy))
            if forw not in visited:
                visited.add(forw)
                visited.add(back)
                
                answer += 1
            
            cx, cy = nx, ny
            
    return answer