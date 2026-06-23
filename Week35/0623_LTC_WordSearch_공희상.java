class Solution {
    private int[] moveRow = {1, -1, 0, 0};
    private int[] moveColumn = {0, 0, 1, -1};

    public boolean exist(char[][] board, String word) {
        int rowSize = board.length;
        int columnSize = board[0].length;

        boolean[][] visited = new boolean[rowSize][columnSize];

        for (int row = 0; row < rowSize; row++) {
            for (int column = 0; column < columnSize; column++) {
                if (dfs(board, word, visited, row, column, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(
            char[][] board,
            String word,
            boolean[][] visited,
            int currentRow,
            int currentColumn,
            int wordIndex
    ) {
        if (wordIndex == word.length()) {
            return true;
        }

        if (currentRow < 0 || currentRow >= board.length
                || currentColumn < 0 || currentColumn >= board[0].length) {
            return false;
        }

        if (visited[currentRow][currentColumn]) {
            return false;
        }

        if (board[currentRow][currentColumn] != word.charAt(wordIndex)) {
            return false;
        }

        visited[currentRow][currentColumn] = true;

        for (int direction = 0; direction < 4; direction++) {
            int nextRow = currentRow + moveRow[direction];
            int nextColumn = currentColumn + moveColumn[direction];

            if (dfs(board, word, visited, nextRow, nextColumn, wordIndex + 1)) {
                return true;
            }
        }

        visited[currentRow][currentColumn] = false;

        return false;
    }
}
