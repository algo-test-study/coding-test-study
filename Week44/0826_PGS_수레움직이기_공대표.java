import java.util.*;

class Solution {
    int n, m;
    int[][] maze;

    int redStart, blueStart;
    int redEnd, blueEnd;

    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int solution(int[][] maze) {
        this.maze = maze;
        n = maze.length;
        m = maze[0].length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (maze[r][c] == 1) {
                    redStart = r * m + c;
                } else if (maze[r][c] == 2) {
                    blueStart = r * m + c;
                } else if (maze[r][c] == 3) {
                    redEnd = r * m + c;
                } else if (maze[r][c] == 4) {
                    blueEnd = r * m + c;
                }
            }
        }

        Queue<State> queue = new LinkedList<>();

        Set<Integer> redVisit = new HashSet<>();
        Set<Integer> blueVisit = new HashSet<>();

        redVisit.add(redStart);
        blueVisit.add(blueStart);

        queue.offer(new State(
                redStart,
                blueStart,
                redVisit,
                blueVisit,
                0
        ));

        while (!queue.isEmpty()) {
            State now = queue.poll();

            if (now.rPos == redEnd && now.bPos == blueEnd) {
                return now.count;
            }

            for (int rDir = 0; rDir < 4; rDir++) {
                int nrPos = now.rPos;
                Set<Integer> nrVisit = new HashSet<>(now.rVisit);

                if (now.rPos != redEnd) {
                    int r = now.rPos / m;
                    int c = now.rPos % m;

                    int nr = r + dr[rDir];
                    int nc = c + dc[rDir];

                    if (!isValid(nr, nc)) {
                        continue;
                    }

                    nrPos = nr * m + nc;

                    if (now.rVisit.contains(nrPos)) {
                        continue;
                    }

                    nrVisit.add(nrPos);
                }

                for (int bDir = 0; bDir < 4; bDir++) {
                    int nbPos = now.bPos;
                    Set<Integer> nbVisit = new HashSet<>(now.bVisit);

                    if (now.bPos != blueEnd) {
                        int r = now.bPos / m;
                        int c = now.bPos % m;

                        int nr = r + dr[bDir];
                        int nc = c + dc[bDir];

                        if (!isValid(nr, nc)) {
                            continue;
                        }

                        nbPos = nr * m + nc;

                        if (now.bVisit.contains(nbPos)) {
                            continue;
                        }

                        nbVisit.add(nbPos);
                    }

                    if (nrPos == nbPos) {
                        continue;
                    }

                    if (nrPos == now.bPos && nbPos == now.rPos) {
                        continue;
                    }

                    queue.offer(new State(
                            nrPos,
                            nbPos,
                            nrVisit,
                            nbVisit,
                            now.count + 1
                    ));
                }
            }
        }

        return 0;
    }

    boolean isValid(int r, int c) {
        return r >= 0 && r < n
                && c >= 0 && c < m
                && maze[r][c] != 5;
    }

    class State {
        int rPos;
        int bPos;
        Set<Integer> rVisit;
        Set<Integer> bVisit;
        int count;

        State(int rPos, int bPos, Set<Integer> rVisit, Set<Integer> bVisit, int count) {
            this.rPos = rPos;
            this.bPos = bPos;
            this.rVisit = rVisit;
            this.bVisit = bVisit;
            this.count = count;
        }
    }
}
