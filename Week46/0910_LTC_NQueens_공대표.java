import java.util.*;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> answer = new ArrayList<>();

        char[][] board = new char[n][n];

        for (int row = 0; row < n; row++) {
            Arrays.fill(board[row], '.');
        }

        backtracking(0, n, board, answer);

        return answer;
    }

    private void backtracking(int row, int n, char[][] board, List<List<String>> answer) {
        if (row == n) {
            List<String> result = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                result.add(new String(board[i]));
            }

            answer.add(result);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!canPlace(row, col, n, board)) {
                continue;
            }

            board[row][col] = 'Q';

            backtracking(row + 1, n, board, answer);

            board[row][col] = '.';
        }
    }

    private boolean canPlace(int row, int col, int n, char[][] board) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        for (int i = 1; row - i >= 0 && col - i >= 0; i++) {
            if (board[row - i][col - i] == 'Q') {
                return false;
            }
        }

        for (int i = 1; row - i >= 0 && col + i < n; i++) {
            if (board[row - i][col + i] == 'Q') {
                return false;
            }
        }

        return true;
    }
}
