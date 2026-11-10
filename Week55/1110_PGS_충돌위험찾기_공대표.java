import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        List<List<Integer>> paths = new ArrayList<>();
        int maxTime = 0;

        for (int i = 0; i < routes.length; i++) {
            List<Integer> path = new ArrayList<>();

            int start = routes[i][0] - 1;
            int r = points[start][0];
            int c = points[start][1];

            path.add(r * 1000 + c);

            for (int j = 1; j < routes[i].length; j++) {
                int target = routes[i][j] - 1;

                int targetR = points[target][0];
                int targetC = points[target][1];

                while (r != targetR) {
                    if (r < targetR) {
                        r++;
                    } else {
                        r--;
                    }

                    path.add(r * 1000 + c);
                }

                while (c != targetC) {
                    if (c < targetC) {
                        c++;
                    } else {
                        c--;
                    }

                    path.add(r * 1000 + c);
                }
            }

            paths.add(path);
            maxTime = Math.max(maxTime, path.size());
        }

        for (int time = 0; time < maxTime; time++) {
            Map<Integer, Integer> count = new HashMap<>();

            for (List<Integer> path : paths) {
                if (time >= path.size()) {
                    continue;
                }

                int position = path.get(time);

                count.put(position, count.getOrDefault(position, 0) + 1);
            }

            for (int value : count.values()) {
                if (value >= 2) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
