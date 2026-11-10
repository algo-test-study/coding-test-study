import java.util.*;

class Solution {

    public int solution(int[][] points, int[][] routes) {
        int robotCount = routes.length;

        int[] rows = new int[robotCount];
        int[] cols = new int[robotCount];
        int[] nextIndex = new int[robotCount];
        boolean[] finished = new boolean[robotCount];

        for (int i = 0; i < robotCount; i++) {
            int startPoint = routes[i][0] - 1;

            rows[i] = points[startPoint][0];
            cols[i] = points[startPoint][1];

            nextIndex[i] = 1;
        }

        int answer = 0;

        while (true) {

            Map<Integer, Integer> counts = new HashMap<>();

            boolean active = false;

            for (int i = 0; i < robotCount; i++) {
                if (finished[i]) {
                    continue;
                }

                active = true;

                int key = rows[i] * 101 + cols[i];

                int count = counts.getOrDefault(key, 0) + 1;
                counts.put(key, count);

                if (count == 2) {
                    answer++;
                }
            }

            if (!active) {
                break;
            }

            for (int i = 0; i < robotCount; i++) {
                if (finished[i]) {
                    continue;
                }

                if (nextIndex[i] == routes[i].length) {
                    finished[i] = true;
                    continue;
                }

                int point = routes[i][nextIndex[i]] - 1;
                int targetRow = points[point][0];
                int targetCol = points[point][1];

                if (rows[i] != targetRow) {
                    rows[i] += Integer.compare(targetRow, rows[i]);
                }

                else if (cols[i] != targetCol) {
                    cols[i] += Integer.compare(targetCol, cols[i]);
                }

                if (rows[i] == targetRow &&
                    cols[i] == targetCol) {

                    nextIndex[i]++;
                }
            }
        }

        return answer;
    }
}
