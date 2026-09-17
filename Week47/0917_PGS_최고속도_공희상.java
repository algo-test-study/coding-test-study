import java.util.*;

class Solution {

    private static final int INF = Integer.MAX_VALUE;
    private static final long NO_POINT = Long.MIN_VALUE;

    // 최대 정점 수가 약 502,600개이므로 20bit면 충분
    private static final int NODE_BITS = 20;
    private static final long NODE_MASK = (1L << NODE_BITS) - 1;

    private Map<Long, Integer> pointId;

    private int[] xs;
    private int[] ys;
    private int[] cameraLimit;

    private int nodeCount;

    public int[] solution(int[][] city, int[][] road) {

        int n = city.length;
        int m = road.length;

        /*
         * 최대 정점 개수
         *
         * 교차점 : mC2
         * 끝점   : 2m
         * 카메라 : m
         * 도시   : n
         */
        int maxNodes =
                m * (m - 1) / 2
                + 3 * m
                + n
                + 5;

        xs = new int[maxNodes];
        ys = new int[maxNodes];

        cameraLimit = new int[maxNodes];
        Arrays.fill(cameraLimit, INF);

        pointId = new HashMap<>(
                (int) (maxNodes / 0.75f) + 1
        );

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] pointsOnRoad = new ArrayList[m];

        for (int i = 0; i < m; i++) {
            pointsOnRoad[i] = new ArrayList<>();
        }

        /*
         * 1. 각 도로의
         *
         * 시작점
         * 끝점
         * 카메라
         *
         * 등록
         */
        for (int i = 0; i < m; i++) {

            int[] r = road[i];

            int start = getPointId(r[0], r[1]);
            int end = getPointId(r[2], r[3]);

            pointsOnRoad[i].add(start);
            pointsOnRoad[i].add(end);

            // 도로 중앙 = 카메라 위치
            int mx = (int) (((long) r[0] + r[2]) / 2);
            int my = (int) (((long) r[1] + r[3]) / 2);

            int camera = getPointId(mx, my);

            pointsOnRoad[i].add(camera);

            // 같은 지점에 여러 카메라가 있을 수 있음
            cameraLimit[camera] =
                    Math.min(cameraLimit[camera], r[4]);
        }

        /*
         * 2. 도시 등록
         */
        int[] cityId = new int[n];

        for (int i = 0; i < n; i++) {
            cityId[i] =
                    getPointId(city[i][0], city[i][1]);
        }

        /*
         * 도시가 어느 도로 위에 있는지 확인
         */
        for (int i = 0; i < m; i++) {

            for (int c = 0; c < n; c++) {

                if (isOnRoad(
                        city[c][0],
                        city[c][1],
                        road[i]
                )) {
                    pointsOnRoad[i].add(cityId[c]);
                }
            }
        }

        /*
         * 3. 모든 도로 쌍의 교차점 계산
         *
         * m <= 1000
         * O(m^2) 가능
         */
        for (int i = 0; i < m; i++) {

            for (int j = i + 1; j < m; j++) {

                long p =
                        intersection(
                                road[i],
                                road[j]
                        );

                if (p == NO_POINT) {
                    continue;
                }

                int x = (int) (p >> 32);
                int y = (int) p;

                int id = getPointId(x, y);

                pointsOnRoad[i].add(id);
                pointsOnRoad[j].add(id);
            }
        }

        DSU dsu = new DSU(nodeCount);

        /*
         * 카메라 때문에 제한이 있는 간선만 저장
         */
        LongList finiteEdges = new LongList();

        /*
         * 4. 각 도로를 작은 구간들로 분리
         */
        for (int i = 0; i < m; i++) {

            ArrayList<Integer> points =
                    pointsOnRoad[i];

            /*
             * 수평 도로
             */
            if (road[i][1] == road[i][3]) {

                points.sort(
                        (a, b) ->
                                Integer.compare(
                                        xs[a],
                                        xs[b]
                                )
                );

            } else {

                /*
                 * 수직 도로
                 */
                points.sort(
                        (a, b) ->
                                Integer.compare(
                                        ys[a],
                                        ys[b]
                                )
                );
            }

            int prev = -1;

            for (int current : points) {

                // 같은 점이 중복 등록될 수 있음
                if (current == prev) {
                    continue;
                }

                if (prev != -1) {

                    int limit =
                            Math.min(
                                    cameraLimit[prev],
                                    cameraLimit[current]
                            );

                    /*
                     * 양 끝점에 카메라가 없다.
                     *
                     * 즉 속도 제한 없이 이동 가능
                     */
                    if (limit == INF) {

                        dsu.union(prev, current);

                    } else {

                        /*
                         * 카메라가 있는 구간
                         */
                        finiteEdges.add(
                                packEdge(
                                        limit,
                                        prev,
                                        current
                                )
                        );
                    }
                }

                prev = current;
            }
        }

        int[] answer = new int[n - 1];

        Arrays.fill(answer, -1);

        int unresolved = n - 1;

        /*
         * 처음부터 카메라 없이 갈 수 있는 도시
         */
        for (int i = 1; i < n; i++) {

            if (dsu.find(cityId[0])
                    == dsu.find(cityId[i])) {

                answer[i - 1] = 0;
                unresolved--;
            }
        }

        /*
         * 5. 제한속도가 높은 간선부터 추가
         */
        long[] edges = finiteEdges.toArray();

        Arrays.sort(edges);

        int index = edges.length - 1;

        while (index >= 0 && unresolved > 0) {

            int limit =
                    unpackLimit(edges[index]);

            /*
             * 같은 제한속도를 가진 간선을
             * 한꺼번에 연결
             */
            while (
                    index >= 0
                    && unpackLimit(edges[index]) == limit
            ) {

                int u = unpackU(edges[index]);
                int v = unpackV(edges[index]);

                dsu.union(u, v);

                index--;
            }

            int sourceRoot =
                    dsu.find(cityId[0]);

            /*
             * 이번 limit에서 새롭게
             * 연결된 도시 확인
             */
            for (int i = 1; i < n; i++) {

                if (answer[i - 1] != -1) {
                    continue;
                }

                if (dsu.find(cityId[i])
                        == sourceRoot) {

                    answer[i - 1] = limit;
                    unresolved--;
                }
            }
        }

        return answer;
    }

    /*
     * 좌표 -> 정점 번호
     */
    private int getPointId(int x, int y) {

        long key = pointKey(x, y);

        Integer existing =
                pointId.get(key);

        if (existing != null) {
            return existing;
        }

        int id = nodeCount++;

        pointId.put(key, id);

        xs[id] = x;
        ys[id] = y;

        return id;
    }

    /*
     * 도시가 도로 위에 있는지 확인
     */
    private boolean isOnRoad(
            int x,
            int y,
            int[] r
    ) {

        // horizontal
        if (r[1] == r[3]) {

            return y == r[1]
                    && r[0] <= x
                    && x <= r[2];
        }

        // vertical
        return x == r[0]
                && r[1] <= y
                && y <= r[3];
    }

    /*
     * 두 도로의 교차점 계산
     */
    private long intersection(
            int[] a,
            int[] b
    ) {

        boolean ah =
                a[1] == a[3];

        boolean bh =
                b[1] == b[3];

        /*
         * 둘 다 수평
         */
        if (ah && bh) {

            if (a[1] != b[1]) {
                return NO_POINT;
            }

            int left =
                    Math.max(a[0], b[0]);

            int right =
                    Math.min(a[2], b[2]);

            /*
             * 문제 조건상
             * 서로 다른 도로는 최대 한 점에서만 만남
             */
            return left == right
                    ? pointKey(left, a[1])
                    : NO_POINT;
        }

        /*
         * 둘 다 수직
         */
        if (!ah && !bh) {

            if (a[0] != b[0]) {
                return NO_POINT;
            }

            int bottom =
                    Math.max(a[1], b[1]);

            int top =
                    Math.min(a[3], b[3]);

            return bottom == top
                    ? pointKey(a[0], bottom)
                    : NO_POINT;
        }

        /*
         * 하나는 수평, 하나는 수직
         */
        int[] h = ah ? a : b;
        int[] v = ah ? b : a;

        int x = v[0];
        int y = h[1];

        if (
                h[0] <= x
                && x <= h[2]
                && v[1] <= y
                && y <= v[3]
        ) {
            return pointKey(x, y);
        }

        return NO_POINT;
    }

    /*
     * x, y를 long 하나로 압축
     */
    private long pointKey(
            int x,
            int y
    ) {

        return ((long) x << 32)
                | (y & 0xffffffffL);
    }

    /*
     * limit, u, v를 long 하나로 압축
     *
     * 메모리 절약
     */
    private long packEdge(
            int limit,
            int u,
            int v
    ) {

        return ((long) limit << 40)
                | ((long) u << 20)
                | v;
    }

    private int unpackLimit(long edge) {

        return (int) (edge >>> 40);
    }

    private int unpackU(long edge) {

        return (int) (
                (edge >>> 20)
                & NODE_MASK
        );
    }

    private int unpackV(long edge) {

        return (int) (
                edge & NODE_MASK
        );
    }

    /*
     * Union-Find
     */
    static class DSU {

        int[] parent;
        int[] size;

        DSU(int n) {

            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {

                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {

            if (parent[x] == x) {
                return x;
            }

            return parent[x] =
                    find(parent[x]);
        }

        void union(int a, int b) {

            int ra = find(a);
            int rb = find(b);

            if (ra == rb) {
                return;
            }

            if (size[ra] < size[rb]) {

                int temp = ra;
                ra = rb;
                rb = temp;
            }

            parent[rb] = ra;
            size[ra] += size[rb];
        }
    }

    /*
     * primitive long 동적 배열
     */
    static class LongList {

        long[] data =
                new long[4096];

        int size;

        void add(long value) {

            if (size == data.length) {

                data = Arrays.copyOf(
                        data,
                        data.length * 2
                );
            }

            data[size++] = value;
        }

        long[] toArray() {

            return Arrays.copyOf(
                    data,
                    size
            );
        }
    }
}
