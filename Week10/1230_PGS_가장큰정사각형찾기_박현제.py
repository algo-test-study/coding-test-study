"""
- 가장 큰 정사각형 구하기
알고리즘
- dp
- 왼 / 위 / 대각선 중 가장 작은값 택 + 1 -> 한변 길이

시간복잡도
- O(nm)

"""
def solution(board):
    n = len(board)
    m = len(board[0])
    
    dp = [[0]*m for i in range(n)]
    
    side = 0
    for i in range(n):
        dp[i][0] = board[i][0]
        if dp[i][0] == 1: side = 1
    for j in range(m):
        dp[0][j] = board[0][j]
        if dp[0][j] == 1: side = 1
        
    
    for i in range(1, n):
        for j in range(1, m):
            if board[i][j] == 1:
                if i == 0 or j == 0:
                    dp[i][j] = board[i][j]
                else:    
                    dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
            side = max(side, dp[i][j])

    return side**2
