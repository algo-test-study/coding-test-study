class Solution {
    boolean found = false;

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m && !found; i++) {
            for (int j = 0; j < n && !found; j++) {
                dfs(board, word, i, j, 0);
            }
        }

        return found;
    }

    private void dfs(char[][] board, String word, int x, int y, int index) {
        if (found) return;

        if (index == word.length()) {
            found = true;
            return;
        }

        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) return;

        if (board[x][y] != word.charAt(index)) return;

        char temp = board[x][y];
        board[x][y] = '#';

        dfs(board, word, x + 1, y, index + 1);
        dfs(board, word, x - 1, y, index + 1);
        dfs(board, word, x, y + 1, index + 1);
        dfs(board, word, x, y - 1, index + 1);

        board[x][y] = temp;
    }
}
