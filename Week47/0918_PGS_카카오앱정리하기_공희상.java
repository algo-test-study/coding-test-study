import java.util.*;

class Solution {

    private int[][] board;
    private int N;
    private int M;

    private final int[] dr = {0, 0, 1, 0, -1};
    private final int[] dc = {0, 1, 0, -1, 0};

    public int[][] solution(int[][] board, int[][] commands) {
        N = board.length;
        M = board[0].length;

        this.board = new int[N][M];

        for (int r = 0; r < N; r++) {
            this.board[r] = board[r].clone();
        }

        for (int[] command : commands) {
            int appId = command[0];
            int dir = command[1];

            execute(appId, dir);
        }

        return this.board;
    }

    private void execute(int appId, int dir) {

        boolean[] group = findPushGroup(appId, dir);
        move(group, dir);
        while (true) {

            int wrappedApp = findWrappedApp(dir);

            if (wrappedApp == 0) {
                break;
            }

            group = findPushGroup(wrappedApp, dir);

            move(group, dir);
        }
    }

    private boolean[] findPushGroup(int start, int dir) {

        boolean[] visited = new boolean[101];

        Queue<Integer> queue = new ArrayDeque<>();

        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {

            int current = queue.poll();

            for (int r = 0; r < N; r++) {

                for (int c = 0; c < M; c++) {

                    if (board[r][c] != current) {
                        continue;
                    }
                    int nr = (r + dr[dir] + N) % N;

                    int nc = (c + dc[dir] + M) % M;

                    int nextApp = board[nr][nc];

                    if (
                        nextApp != 0
                        && !visited[nextApp]
                    ) {

                        visited[nextApp] = true;
                        queue.offer(nextApp);
                    }
                }
            }
        }

        return visited;
    }


    private void move(boolean[] group, int dir) {

        List<Cell> cells = new ArrayList<>();

        for (int r = 0; r < N; r++) {

            for (int c = 0; c < M; c++) {

                int app = board[r][c];

                if (
                    app != 0
                    && group[app]
                ) {

                    cells.add(
                        new Cell(r, c, app)
                    );
                }
            }
        }

        /*
         * 기존 위치를 먼저 비운다.
         *
         * 동시에 이동하는 효과를 만들기 위함.
         */
        for (Cell cell : cells) {
            board[cell.r][cell.c] = 0;
        }

        /*
         * 한 칸 이동
         */
        for (Cell cell : cells) {

            int nr =
                (cell.r + dr[dir] + N) % N;

            int nc =
                (cell.c + dc[dir] + M) % M;

            board[nr][nc] = cell.id;
        }
    }

    private int findWrappedApp(int dir) {

        if (dir == 1 || dir == 3) {

            for (int r = 0; r < N; r++) {

                int app = board[r][0];

                if (app == 0
                    || board[r][M - 1] != app) 
                { continue; }
 
                boolean split = false;

                for (int c = 0; c < M; c++) {

                    if (board[r][c] != app) {
                        split = true;
                        break;
                    }
                }

                if (split) {
                    return app;
                }
            }
        }
        
        else {

            for (int c = 0; c < M; c++) {

                int app = board[0][c];

                if (
                    app == 0
                    || board[N - 1][c] != app
                ) {
                    continue;
                }

                boolean split = false;

                for (int r = 0; r < N; r++) {

                    if (board[r][c] != app) {
                        split = true;
                        break;
                    }
                }

                if (split) {
                    return app;
                }
            }
        }

        return 0;
    }

    private static class Cell {

        int r;
        int c;
        int id;

        Cell(int r, int c, int id) {
            this.r = r;
            this.c = c;
            this.id = id;
        }
    }
}
