#dp, O(n*m)
def solution(board):
    answer = 0
    
    n = len(board)
    m = len(board[0])
    
    dp = [[0]*m for _ in range(n)]
    
    for i in range(n):
        for j in range(m):
            if board[i][j] == 1:
                if i == 0 or j == 0:
                    dp[i][j] = 1
                else:
                    dp[i][j] = min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1]) +1 
                
                # answer = max(answer, dp[i][j]**2) #시간초과

    for r in dp:
        answer = max(answer, max(r) ** 2)
    
                
    return answer
