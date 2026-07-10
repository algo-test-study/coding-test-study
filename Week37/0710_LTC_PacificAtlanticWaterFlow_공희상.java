import java.util.ArrayList;
import java.util.List;

class Solution {
    private final int[] moveRow = {1, -1, 0, 0};
    private final int[] moveColumn = {0, 0, 1, -1};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rowCount = heights.length;
        int columnCount = heights[0].length;

        boolean[][] reachableFromPacific =
                new boolean[rowCount][columnCount];

        boolean[][] reachableFromAtlantic =
                new boolean[rowCount][columnCount];

        // 태평양: 위쪽 경계, 왼쪽 경계
        for (int column = 0; column < columnCount; column++) {
            dfs(
                    heights,
                    reachableFromPacific,
                    0,
                    column
            );
        }

        for (int row = 0; row < rowCount; row++) {
            dfs(
                    heights,
                    reachableFromPacific,
                    row,
                    0
            );
        }

        // 대서양: 아래쪽 경계, 오른쪽 경계
        for (int column = 0; column < columnCount; column++) {
            dfs(
                    heights,
                    reachableFromAtlantic,
                    rowCount - 1,
                    column
            );
        }

        for (int row = 0; row < rowCount; row++) {
            dfs(
                    heights,
                    reachableFromAtlantic,
                    row,
                    columnCount - 1
            );
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                if (reachableFromPacific[row][column]
                        && reachableFromAtlantic[row][column]) {
                    result.add(List.of(row, column));
                }
            }
        }

        return result;
    }

    private void dfs(
            int[][] heights,
            boolean[][] visited,
            int currentRow,
            int currentColumn
    ) {
        visited[currentRow][currentColumn] = true;

        for (int direction = 0; direction < 4; direction++) {
            int nextRow = currentRow + moveRow[direction];
            int nextColumn = currentColumn + moveColumn[direction];

            if (nextRow < 0 || nextRow >= heights.length
                    || nextColumn < 0
                    || nextColumn >= heights[0].length) {
                continue;
            }

            if (visited[nextRow][nextColumn]) {
                continue;
            }

            if (heights[nextRow][nextColumn]
                    < heights[currentRow][currentColumn]) {
                continue;
            }

            dfs(heights, visited, nextRow, nextColumn);
        }
    }
}
