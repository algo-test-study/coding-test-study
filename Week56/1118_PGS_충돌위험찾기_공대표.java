class Solution {
    public int solution(int[][] points, int[][] routes) {
        int robotCount = routes.length;
        int routeLength = routes[0].length;

        int[] rows = new int[robotCount];
        int[] cols = new int[robotCount];
        int[] next = new int[robotCount];

        for (int i = 0; i < robotCount; i++) {
            int[] start = points[routes[i][0] - 1];
            rows[i] = start[0];
            cols[i] = start[1];
            next[i] = 1;
        }

        int[][] count = new int[101][101];
        int remaining = robotCount;
        int answer = 0;

        while (remaining > 0) {
            // 이번 초에 위험 상황이 발생한 좌표를 집계한다.
            for (int i = 0; i < robotCount; i++) {
                if (next[i] == routeLength) {
                    continue;
                }

                if (++count[rows[i]][cols[i]] == 2) {
                    answer++;
                }
            }

            for (int i = 0; i < robotCount; i++) {
                if (next[i] == routeLength) {
                    continue;
                }

                // 이번 초에 사용한 칸만 초기화한다.
                count[rows[i]][cols[i]] = 0;
                int[] target = points[routes[i][next[i]] - 1];

                if (rows[i] == target[0] && cols[i] == target[1]) {
                    next[i]++;

                    if (next[i] == routeLength) {
                        remaining--;
                        continue;
                    }

                    target = points[routes[i][next[i]] - 1];
                }

                // r이 맞을 때까지 r만 움직이고, 그다음 c를 움직인다.
                if (rows[i] != target[0]) {
                    rows[i] += Integer.compare(target[0], rows[i]);
                } else {
                    cols[i] += Integer.compare(target[1], cols[i]);
                }
            }
        }

        return answer;
    }
}
