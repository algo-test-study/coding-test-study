class Solution {
    public int uniquePaths(int m, int n) {
        int[][] pathCounts = new int[m][n];

        for (int row = 0; row < m; row++) {
            pathCounts[row][0] = 1;
        }

        for (int column = 0; column < n; column++) {
            pathCounts[0][column] = 1;
        }

        for (int row = 1; row < m; row++) {
            for (int column = 1; column < n; column++) {
                pathCounts[row][column] =
                        pathCounts[row - 1][column]
                        + pathCounts[row][column - 1];
            }
        }

        return pathCounts[m - 1][n - 1];
    }
}
