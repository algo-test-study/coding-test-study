class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        return dfs(board, aloc[0], aloc[1], bloc[0], bloc[1])[1];
    }

    int[] dfs(int[][] board, int ax, int ay, int bx, int by) {
        if (board[ax][ay] == 0) {
            return new int[]{0, 0};
        }

        int n = board.length;
        int m = board[0].length;

        boolean canMove = false;
        int winMin = Integer.MAX_VALUE;
        int loseMax = 0;

        for (int i = 0; i < 4; i++) {
            int nx = ax + dx[i];
            int ny = ay + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if (board[nx][ny] == 0) continue;

            canMove = true;

            board[ax][ay] = 0;

            int[] result = dfs(board, bx, by, nx, ny);

            board[ax][ay] = 1;

            if (result[0] == 0) {
                winMin = Math.min(winMin, result[1] + 1);
            } else {
                loseMax = Math.max(loseMax, result[1] + 1);
            }
        }

        if (!canMove) {
            return new int[]{0, 0};
        }

        if (winMin != Integer.MAX_VALUE) {
            return new int[]{1, winMin};
        }

        return new int[]{0, loseMax};
    }
}
