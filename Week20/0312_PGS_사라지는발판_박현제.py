"""
알고리즘
- 백트래킹, 완탐
시간복잡도
- O(n*m)

"""
def solution(board, aloc, bloc):
    answer = -1

    n = len(board)
    m = len(board[0])
  
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    def dfs(turn, ax, ay, bx, by):
        if turn == 0:
            x, y = ax, ay
        else:
            x, y = bx, by

        if board[x][y] == 0:
            return False, 0
        
        is_move = False
        
        win = False
        win_cnt = float('inf')
        lose_cnt = 0
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            
            if 0 <= nx < n and 0 <= ny < m and board[nx][ny] == 1:
                is_move = True
                
                board[x][y] = 0
                
                if turn == 0:
                    next_win, cnt = dfs(1, nx, ny, bx, by)
                else:
                    next_win, cnt = dfs(0, ax, ay, nx, ny)
                
                board[x][y] = 1
                
                if not next_win:
                    win = True
                    win_cnt = min(win_cnt, cnt + 1)
                else:
                    lose_cnt = max(lose_cnt, cnt + 1)
        
        if not is_move:
            return False, 0
        
        if win:
            return True, win_cnt
        
        return False, lose_cnt
    
    answer = dfs(0, aloc[0], aloc[1], bloc[0], bloc[1])[1]
    return answer
