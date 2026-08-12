import java.util.Arrays;

class Solution {

    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int INF = drops.length + 1;

        int[] rainTime = new int[m * n];
        Arrays.fill(rainTime, INF);

        for (int i = 0; i < drops.length; i++) {
            int r = drops[i][0];
            int c = drops[i][1];
            rainTime[r * n + c] = i + 1;
        }

        int windowCols = n - w + 1;
        int[] rowMin = new int[m * windowCols];
        int[] deque = new int[Math.max(m, n)];

        for (int r = 0; r < m; r++) {
            int head = 0;
            int tail = 0;

            int rainBase = r * n;
            int resultBase = r * windowCols;

            for (int c = 0; c < n; c++) {
                while (head < tail && deque[head] <= c - w) {
                    head++;
                }

                int currentValue = rainTime[rainBase + c];

                while (head < tail &&
                        rainTime[rainBase + deque[tail - 1]] >= currentValue) {
                    tail--;
                }

                deque[tail++] = c;

                if (c >= w - 1) {
                    int startCol = c - w + 1;
                    rowMin[resultBase + startCol] =
                            rainTime[rainBase + deque[head]];
                }
            }
        }

        int bestScore = -1;
        int bestRow = 0;
        int bestCol = 0;

        for (int c = 0; c < windowCols; c++) {
            int head = 0;
            int tail = 0;

            for (int r = 0; r < m; r++) {
                while (head < tail && deque[head] <= r - h) {
                    head++;
                }

                int currentValue = rowMin[r * windowCols + c];

                while (head < tail &&
                        rowMin[deque[tail - 1] * windowCols + c] >= currentValue) {
                    tail--;
                }

                deque[tail++] = r;

                if (r >= h - 1) {
                    int startRow = r - h + 1;
                    int score = rowMin[deque[head] * windowCols + c];

                    if (score > bestScore ||
                            (score == bestScore &&
                                    (startRow < bestRow ||
                                            (startRow == bestRow && c < bestCol)))) {
                        bestScore = score;
                        bestRow = startRow;
                        bestCol = c;
                    }
                }
            }
        }

        return new int[]{bestRow, bestCol};
    }
}
