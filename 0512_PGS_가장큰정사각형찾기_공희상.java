class Solution {
    public int solution(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        int max = 0;
        
        for (int i = 0; i < row; i++) {
            max = Math.max(max, board[i][0]);
        }
        for (int i = 0; i < col; i++) {
            max = Math.max(max, board[0][i]);
        }
        
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == 1) {
                    board[i][j] = Math.min(
                        Math.min(board[i-1][j], board[i][j-1]), board[i-1][j-1]
                    ) + 1;
                    
                    max = Math.max(max, board[i][j]);
                }
            }
        }
        
        return max * max;
    }
}
