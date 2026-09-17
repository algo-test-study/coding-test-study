import java.util.*;

class Solution {
    public int[] solution(int[][] city, int[][] road) {
        int n = city.length;
        int m = road.length;
        long INF = 1_000_000_001L;

        List<long[]> points = new ArrayList<>();
        Map<String, Integer> pointMap = new HashMap<>();

        for (int[] c : city) {
            String key = c[0] + "," + c[1];

            if (!pointMap.containsKey(key)) {
                pointMap.put(key, points.size());
                points.add(new long[]{c[0], c[1], -1});
            }
        }

        for (int[] r : road) {
            long[][] positions = {
                {r[0], r[1], -1},
                {r[2], r[3], -1},
                {(long) r[0] + r[2] >> 1, (long) r[1] + r[3] >> 1, r[4]}
            };

            for (long[] position : positions) {
                String key = position[0] + "," + position[1];

                if (!pointMap.containsKey(key)) {
                    pointMap.put(key, points.size());
                    points.add(position.clone());
                } else if (position[2] != -1) {
                    int index = pointMap.get(key);

                    if (points.get(index)[2] == -1) {
                        points.get(index)[2] = position[2];
                    } else {
                        points.get(index)[2] = Math.min(points.get(index)[2], position[2]);
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                boolean verticalI = road[i][0] == road[i][2];
                boolean verticalJ = road[j][0] == road[j][2];

                if (verticalI == verticalJ) {
                    continue;
                }

                int vertical = verticalI ? i : j;
                int horizontal = verticalI ? j : i;

                long x = road[vertical][0];
                long y = road[horizontal][1];

                if (road[vertical][1] <= y && y <= road[vertical][3]
                        && road[horizontal][0] <= x && x <= road[horizontal][2]) {

                    String key = x + "," + y;

                    if (!pointMap.containsKey(key)) {
                        pointMap.put(key, points.size());
                        points.add(new long[]{x, y, -1});
                    }
                }
            }
        }

        int pointCount = points.size();

        List<List<long[]>> graph = new ArrayList<>();

        for (int i = 0; i < pointCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            List<Integer> roadPoints = new ArrayList<>();

            for (int j = 0; j < pointCount; j++) {
                long x = points.get(j)[0];
                long y = points.get(j)[1];

                boolean onRoad;

                if (road[i][0] == road[i][2]) {
                    onRoad = x == road[i][0]
                            && road[i][1] <= y
                            && y <= road[i][3];
                } else {
                    onRoad = y == road[i][1]
                            && road[i][0] <= x
                            && x <= road[i][2];
                }

                if (onRoad) {
                    roadPoints.add(j);
                }
            }

            if (road[i][0] == road[i][2]) {
                roadPoints.sort((a, b) ->
                        Long.compare(points.get(a)[1], points.get(b)[1]));
            } else {
                roadPoints.sort((a, b) ->
                        Long.compare(points.get(a)[0], points.get(b)[0]));
            }

            for (int j = 0; j + 1 < roadPoints.size(); j++) {
                int from = roadPoints.get(j);
                int to = roadPoints.get(j + 1);

                long speed = INF;

                if (points.get(from)[2] != -1) {
                    speed = Math.min(speed, points.get(from)[2]);
                }

                if (points.get(to)[2] != -1) {
                    speed = Math.min(speed, points.get(to)[2]);
                }

                graph.get(from).add(new long[]{to, speed});
                graph.get(to).add(new long[]{from, speed});
            }
        }

        int start = pointMap.get(city[0][0] + "," + city[0][1]);

        long[] best = new long[pointCount];
        Arrays.fill(best, -1);
        best[start] = INF;

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[1], a[1]));

        pq.offer(new long[]{start, INF});

        while (!pq.isEmpty()) {
            long[] current = pq.poll();

            int now = (int) current[0];
            long speed = current[1];

            if (speed < best[now]) {
                continue;
            }

            for (long[] edge : graph.get(now)) {
                int next = (int) edge[0];
                long nextSpeed = Math.min(speed, edge[1]);

                if (nextSpeed > best[next]) {
                    best[next] = nextSpeed;
                    pq.offer(new long[]{next, nextSpeed});
                }
            }
        }

        int[] answer = new int[n - 1];

        for (int i = 1; i < n; i++) {
            int index = pointMap.get(city[i][0] + "," + city[i][1]);

            answer[i - 1] = best[index] == INF ? 0 : (int) best[index];
        }

        return answer;
    }
}
