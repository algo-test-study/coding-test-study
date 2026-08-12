class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int left = 0;
        int right = drops.length;

        while (left < right) {
            int mid = (left + right + 1) / 2;

            int[][] map = new int[m + 1][n + 1];

            for (int i = 0; i < mid; i++) {
                int r = drops[i][0] + 1;
                int c = drops[i][1] + 1;

                map[r][c] = 1;
            }

            for (int r = 1; r <= m; r++) {
                for (int c = 1; c <= n; c++) {
                    map[r][c] += map[r - 1][c] + map[r][c - 1] - map[r - 1][c - 1];
                }
            }

            boolean possible = false;

            for (int r = 0; r <= m - h; r++) {
                for (int c = 0; c <= n - w; c++) {
                    int bottom = r + h;
                    int rightColumn = c + w;

                    int rainCount = map[bottom][rightColumn] - map[r][rightColumn] - map[bottom][c] + map[r][c];

                    if (rainCount == 0) {
                        possible = true;
                        break;
                    }
                }

                if (possible) {
                    break;
                }
            }

            if (possible) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        int[][] map = new int[m + 1][n + 1];

        for (int i = 0; i < left; i++) {
            int r = drops[i][0] + 1;
            int c = drops[i][1] + 1;

            map[r][c] = 1;
        }

        for (int r = 1; r <= m; r++) {

            for (int c = 1; c <= n; c++) {
                map[r][c] += map[r - 1][c] + map[r][c - 1] - map[r - 1][c - 1];
            }
        }

        for (int r = 0; r <= m - h; r++) {
            for (int c = 0; c <= n - w; c++) {
                int bottom = r + h;
                int rightColumn = c + w;

                int rainCount = map[bottom][rightColumn] - map[r][rightColumn] - map[bottom][c] + map[r][c];

                if (rainCount == 0) {
                    return new int[]{r, c};
                }
            }
        }

        return new int[]{-1, -1};
    }
}
