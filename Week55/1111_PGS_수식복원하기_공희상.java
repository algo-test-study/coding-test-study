class Solution {

    private int[][] maze;
    private int rows, cols;

    private boolean[][] redVisited;
    private boolean[][] blueVisited;

    private int answer;

    private final int[] dr = {-1, 1, 0, 0};
    private final int[] dc = {0, 0, -1, 1};

    public int solution(int[][] maze) {
        this.maze = maze;
        rows = maze.length;
        cols = maze[0].length;

        redVisited = new boolean[rows][cols];
        blueVisited = new boolean[rows][cols];

        answer = Integer.MAX_VALUE;

        int rr = 0, rc = 0;
        int br = 0, bc = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (maze[r][c] == 1) {
                    rr = r;
                    rc = c;
                }

                if (maze[r][c] == 2) {
                    br = r;
                    bc = c;
                }
            }
        }

        redVisited[rr][rc] = true;
        blueVisited[br][bc] = true;

        dfs(rr, rc, br, bc, 0);

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    private void dfs(int rr, int rc,
                     int br, int bc, int turns) {
        if (turns >= answer) {
            return;
        }

        boolean redDone = maze[rr][rc] == 3;
        boolean blueDone = maze[br][bc] == 4;

        if (redDone && blueDone) {
            answer = turns;
            return;
        }
        for (int rd = 0; rd < (redDone ? 1 : 4); rd++) {

            int nr = redDone ? rr : rr + dr[rd];
            int nc = redDone ? rc : rc + dc[rd];

            if (!redDone && !canMove(nr, nc, redVisited)) {
                continue;
            }

            // 5. 파란 수레 이동
            for (int bd = 0; bd < (blueDone ? 1 : 4); bd++) {

                int nbR = blueDone ? br : br + dr[bd];
                int nbC = blueDone ? bc : bc + dc[bd];

                if (!blueDone &&
                    !canMove(nbR, nbC, blueVisited)) {
                    continue;
                }

                if (nr == nbR && nc == nbC) {
                    continue;
                }

                if (nr == br && nc == bc &&
                    nbR == rr && nbC == rc) {
                    continue;
                }

                if (!redDone) {
                    redVisited[nr][nc] = true;
                }

                if (!blueDone) {
                    blueVisited[nbR][nbC] = true;
                }

                // 7. 다음 턴 탐색
                dfs(nr, nc, nbR, nbC, turns + 1);

                // 8. 백트래킹: 방문 상태 복구
                if (!redDone) {
                    redVisited[nr][nc] = false;
                }

                if (!blueDone) {
                    blueVisited[nbR][nbC] = false;
                }
            }
        }
    }

    private boolean canMove(int r, int c,
                            boolean[][] visited) {

        return r >= 0 && r < rows
            && c >= 0 && c < cols
            && maze[r][c] != 5
            && !visited[r][c];
    }
}
