dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]

def dfs(board, aloc, bloc, turn, memo):
    key = (board, aloc[0], aloc[1], bloc[0], bloc[1], turn)

    if key in memo:
        return memo[key]
    
    y, x = aloc if turn % 2 == 0 else bloc
        
    if board[y][x] == 0:
        key = (board, aloc[0], aloc[1], bloc[0], bloc[1], turn)
        memo[key] = (False, 0)
        return memo[key]
    
    win = False
    min_turn = float('inf')
    max_turn = 0
    
    cur_board = [list(row) for row in board]
    cur_board[y][x] = 0

    for k in range(4):
        nx, ny = x+dx[k], y+dy[k]
        if nx < 0 or nx >= len(board[0]) or ny < 0 or ny >= len(board) or board[ny][nx] == 0:
            continue
        nboard = tuple(tuple(row) for row in cur_board)
        
        if turn % 2 == 0:
            result, cnt = dfs(nboard, [ny, nx], bloc, turn+1, memo)
        else:
            result, cnt = dfs(nboard, aloc, [ny, nx], turn+1, memo)
            
        if not result:
            win = True
            min_turn = min(min_turn, cnt+1)
        else:
            max_turn = max(max_turn, cnt+1)
    cur_board[y][x] = 1

    if not win and min_turn == float('inf') and max_turn == 0:
        memo[key] = (False, 0)
        return memo[key]
    
    if win:
        memo[key] = (True, min_turn)
    else:
        memo[key] = (False, max_turn)
        
    return memo[key]
            
def solution(board, aloc, bloc):
    answer = -1
    memo = {}
    nboard = tuple(tuple(row) for row in board)
    _, answer = dfs(nboard, aloc, bloc, 0, memo)
    return answer
