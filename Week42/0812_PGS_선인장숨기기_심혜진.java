import java.util.*;

class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int INF = drops.length + 1;
        int[][] t = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(t[i], INF);
        }
        for (int i = 0; i < drops.length; i++) {
            int r = drops[i][0];
            int c = drops[i][1];
            t[r][c] = i + 1;
        }

        int[][] ps = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int rowSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += t[i][j];
                ps[i + 1][j + 1] = ps[i][j + 1] + rowSum;
            }
        }

        long bestSum = -1;
        int bestR = 0;
        int bestC = 0;

        for (int r = 0; r + h <= m; r++) {
            int r2 = r + h;
            for (int c = 0; c + w <= n; c++) {
                int c2 = c + w;
                long sum = ps[r2][c2] - ps[r][c2] - ps[r2][c] + ps[r][c];
                if (sum > bestSum) {
                    bestSum = sum;
                    bestR = r;
                    bestC = c;
                }
            }
        }

        return new int[]{bestR, bestC};
    }
}
