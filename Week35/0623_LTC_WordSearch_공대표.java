class Solution {
    private int m;
    private int n;
    private boolean[][] visited;
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;

        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int x, int y, int index) {
        if (index == word.length()) {
            return true;
        }

        if (x < 0 || y < 0 || x >= m || y >= n) {
            return false;
        }

        if (visited[x][y]) {
            return false;
        }

        if (board[x][y] != word.charAt(index)) {
            return false;
        }

        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (dfs(board, word, nx, ny, index + 1)) {
                return true;
            }
        }

        visited[x][y] = false;

        return false;
    }
}
