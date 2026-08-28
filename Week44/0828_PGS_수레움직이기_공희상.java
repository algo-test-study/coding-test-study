class Solution {

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private int[][] maze;
    private int rows;
    private int cols;

    private int redEndRow;
    private int redEndCol;
    private int blueEndRow;
    private int blueEndCol;

    private boolean[][] redVisited;
    private boolean[][] blueVisited;

    private int answer = Integer.MAX_VALUE;

    public int solution(int[][] maze) {
        this.maze = maze;
        this.rows = maze.length;
        this.cols = maze[0].length;

        int redStartRow = 0;
        int redStartCol = 0;
        int blueStartRow = 0;
        int blueStartCol = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (maze[r][c] == 1) {
                    redStartRow = r;
                    redStartCol = c;
                } else if (maze[r][c] == 2) {
                    blueStartRow = r;
                    blueStartCol = c;
                } else if (maze[r][c] == 3) {
                    redEndRow = r;
                    redEndCol = c;
                } else if (maze[r][c] == 4) {
                    blueEndRow = r;
                    blueEndCol = c;
                }
            }
        }

        redVisited = new boolean[rows][cols];
        blueVisited = new boolean[rows][cols];

        redVisited[redStartRow][redStartCol] = true;
        blueVisited[blueStartRow][blueStartCol] = true;

        dfs(
                redStartRow,
                redStartCol,
                blueStartRow,
                blueStartCol,
                0
        );

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    private void dfs(
            int redRow,
            int redCol,
            int blueRow,
            int blueCol,
            int turn
    ) {

        if (turn >= answer) {
            return;
        }

        boolean redFinished =
                redRow == redEndRow && redCol == redEndCol;

        boolean blueFinished =
                blueRow == blueEndRow && blueCol == blueEndCol;

        if (redFinished && blueFinished) {
            answer = turn;
            return;
        }

        for (int redDir = -1; redDir < 4; redDir++) {

            if (redFinished && redDir != -1) {
                continue;
            }

            if (!redFinished && redDir == -1) {
                continue;
            }

            int nextRedRow = redRow;
            int nextRedCol = redCol;

            if (!redFinished) {
                nextRedRow += DR[redDir];
                nextRedCol += DC[redDir];

                if (!canMoveRed(nextRedRow, nextRedCol)) {
                    continue;
                }
            }

            for (int blueDir = -1; blueDir < 4; blueDir++) {

                if (blueFinished && blueDir != -1) {
                    continue;
                }

                if (!blueFinished && blueDir == -1) {
                    continue;
                }

                int nextBlueRow = blueRow;
                int nextBlueCol = blueCol;

                if (!blueFinished) {
                    nextBlueRow += DR[blueDir];
                    nextBlueCol += DC[blueDir];

                    if (!canMoveBlue(nextBlueRow, nextBlueCol)) {
                        continue;
                    }
                }
                
               if (nextRedRow == nextBlueRow
                        && nextRedCol == nextBlueCol) {
                    continue;
                }

              
                if (nextRedRow == blueRow
                        && nextRedCol == blueCol
                        && nextBlueRow == redRow
                        && nextBlueCol == redCol) {
                    continue;
                }

                if (!redFinished) {
                    redVisited[nextRedRow][nextRedCol] = true;
                }

                if (!blueFinished) {
                    blueVisited[nextBlueRow][nextBlueCol] = true;
                }

                dfs(
                        nextRedRow,
                        nextRedCol,
                        nextBlueRow,
                        nextBlueCol,
                        turn + 1
                );

                if (!redFinished) {
                    redVisited[nextRedRow][nextRedCol] = false;
                }

                if (!blueFinished) {
                    blueVisited[nextBlueRow][nextBlueCol] = false;
                }
            }
        }
    }

    private boolean canMoveRed(int row, int col) {
        if (!isInRange(row, col)) {
            return false;
        }

        if (maze[row][col] == 5) {
            return false;
        }

        return !redVisited[row][col];
    }

    private boolean canMoveBlue(int row, int col) {
        if (!isInRange(row, col)) {
            return false;
        }

        if (maze[row][col] == 5) {
            return false;
        }

        return !blueVisited[row][col];
    }

    private boolean isInRange(int row, int col) {
        return row >= 0
                && row < rows
                && col >= 0
                && col < cols;
    }
}
