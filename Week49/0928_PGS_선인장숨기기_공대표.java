import java.util.*;

class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int INF = drops.length + 1;

        int[][] rain = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(rain[i], INF);
        }

        for (int i = 0; i < drops.length; i++) {
            rain[drops[i][0]][drops[i][1]] = i + 1;
        }

        int width = n - w + 1;
        int height = m - h + 1;

        int[][] horizontal = new int[m][width];

        for (int r = 0; r < m; r++) {
            Deque<Integer> deque = new ArrayDeque<>();

            for (int c = 0; c < n; c++) {
                while (!deque.isEmpty() && rain[r][deque.peekLast()] >= rain[r][c]) {
                    deque.pollLast();
                }

                deque.offerLast(c);

                while (!deque.isEmpty() && deque.peekFirst() <= c - w) {
                    deque.pollFirst();
                }

                if (c >= w - 1) {
                    horizontal[r][c - w + 1] = rain[r][deque.peekFirst()];
                }
            }
        }

        int[][] firstRain = new int[height][width];

        for (int c = 0; c < width; c++) {
            Deque<Integer> deque = new ArrayDeque<>();

            for (int r = 0; r < m; r++) {
                while (!deque.isEmpty()
                        && horizontal[deque.peekLast()][c] >= horizontal[r][c]) {
                    deque.pollLast();
                }

                deque.offerLast(r);

                while (!deque.isEmpty() && deque.peekFirst() <= r - h) {
                    deque.pollFirst();
                }

                if (r >= h - 1) {
                    firstRain[r - h + 1][c] = horizontal[deque.peekFirst()][c];
                }
            }
        }

        int answerRow = 0;
        int answerCol = 0;
        int latest = -1;

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                if (firstRain[r][c] > latest) {
                    latest = firstRain[r][c];
                    answerRow = r;
                    answerCol = c;
                }
            }
        }

        return new int[]{answerRow, answerCol};
    }
}
