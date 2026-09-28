import java.util.Arrays;

class Solution {

    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int neverWet = drops.length + 1;

        int[] rainTime = new int[m * n];
        Arrays.fill(rainTime, neverWet);

        for (int i = 0; i < drops.length; i++) {
            int r = drops[i][0];
            int c = drops[i][1];

            rainTime[r * n + c] = i + 1;
        }

        int horizontalWidth = n - w + 1;
        int[] horizontalMin = new int[m * horizontalWidth];

        int[] deque = new int[Math.max(m, n)];

        for (int r = 0; r < m; r++) {
            int head = 0;
            int tail = 0;

            int rowBase = r * n;
            int resultBase = r * horizontalWidth;

            for (int c = 0; c < n; c++) {
                int current = rainTime[rowBase + c];

                while (
                    head < tail
                        && rainTime[rowBase + deque[tail - 1]] >= current
                ) {
                    tail--;
                }

                deque[tail++] = c;

                while (head < tail && deque[head] <= c - w) {
                    head++;
                }

                if (c >= w - 1) {
                    horizontalMin[resultBase + c - w + 1]
                        = rainTime[rowBase + deque[head]];
                }
            }
        }

        int bestTime = -1;
        int bestRow = 0;
        int bestCol = 0;

        for (int c = 0; c < horizontalWidth; c++) {
            int head = 0;
            int tail = 0;

            for (int r = 0; r < m; r++) {
                int current = horizontalMin[r * horizontalWidth + c];

                while (
                    head < tail
                        && horizontalMin[deque[tail - 1] * horizontalWidth + c]
                        >= current
                ) {
                    tail--;
                }

                deque[tail++] = r;

                while (head < tail && deque[head] <= r - h) {
                    head++;
                }

                if (r >= h - 1) {
                    int topRow = r - h + 1;

                    int firstRain =
                        horizontalMin[deque[head] * horizontalWidth + c];

                    if (
                        firstRain > bestTime
                            || (
                                firstRain == bestTime
                                    && (
                                        topRow < bestRow
                                            || (
                                                topRow == bestRow
                                                    && c < bestCol
                                            )
                                    )
                            )
                    ) {
                        bestTime = firstRain;
                        bestRow = topRow;
                        bestCol = c;
                    }
                }
            }
        }

        return new int[]{bestRow, bestCol};
    }
}
