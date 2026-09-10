class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];

        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        backtrack(0, n, board, result);

        return result;
    }

    private void backtrack(
            int row,
            int n,
            char[][] board,
            List<List<String>> result
    ) {
        if (row == n) {
            result.add(createBoard(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col, n)) {
                continue;
            }

            board[row][col] = 'Q';
            backtrack(row + 1, n, board, result);
            board[row][col] = '.';
        }
    }

    private boolean isValid(char[][] board, int row, int col, int n) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0;
             i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col + 1;
             i >= 0 && j < n;
             i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private List<String> createBoard(char[][] board) {
        List<String> result = new ArrayList<>();

        for (char[] row : board) {
            result.add(new String(row));
        }

        return result;
    }
}
