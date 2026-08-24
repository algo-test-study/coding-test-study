"""
O(N*len(moves))
"""
def solution(board, moves):
    answer = 0
    stk = []
    n = len(board)
    cnt = 0
    for j in moves:
        curr = 0
        idx = 0
        while idx < n:
            if board[idx][j - 1] != 0:
                curr = board[idx][j - 1]
                board[idx][j - 1] = 0
                break
            idx += 1
            
        if idx < n:
            if stk and stk[-1] == curr:
                stk.pop()
                cnt += 2
            else:
                stk.append(curr)
    
    return cnt
