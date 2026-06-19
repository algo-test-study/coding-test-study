import java.util.*;

class Solution {

    private final int[] dx = {1, -1, 0, 0};
    private final int[] dy = {0, 0, 1, -1};

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public int solution(int[][] land, int height) {
        int n = land.length;

        int[][] groups = new int[n][n];

        int groupCount = makeGroups(land, groups, height);

        PriorityQueue<Edge> edges = buildEdges(land, groups, groupCount);

        return kruskal(edges, groupCount);
    }

    private int makeGroups(int[][] land, int[][] groups, int height) {
        int n = land.length;
        int groupId = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (groups[i][j] != 0) {
                    continue;
                }

                bfs(land, groups, i, j, ++groupId, height);
            }
        }

        return groupId;
    }

    private void bfs(int[][] land, int[][] groups, int startX, int startY, int groupId, int height) {

        int n = land.length;

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{startX, startY});
        groups[startX][startY] = groupId;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int x = current[0];
            int y = current[1];

            for (int d = 0; d < 4; d++) {

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!isValid(nx, ny, n)) {
                    continue;
                }

                if (groups[nx][ny] != 0) {
                    continue;
                }

                if (Math.abs(land[x][y] - land[nx][ny]) > height) {
                    continue;
                }

                groups[nx][ny] = groupId;
                queue.offer(new int[]{nx, ny});
            }
        }
    }

    private PriorityQueue<Edge> buildEdges(int[][] land, int[][] groups, int groupCount) {

        int n = land.length;

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int x = 0; x < n; x++) {

            for (int y = 0; y < n; y++) {

                for (int d = 0; d < 4; d++) {

                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (!isValid(nx, ny, n)) {
                        continue;
                    }

                    int from = groups[x][y];
                    int to = groups[nx][ny];

                    if (from == to) {
                        continue;
                    }

                    int cost =
                            Math.abs(land[x][y] - land[nx][ny]);

                    pq.offer(new Edge(from, to, cost));
                }
            }
        }

        return pq;
    }

    private int kruskal(PriorityQueue<Edge> edges, int groupCount) {

        int[] parent = new int[groupCount + 1];

        for (int i = 1; i <= groupCount; i++) {
            parent[i] = i;
        }

        int totalCost = 0;
        int connected = 0;

        while (!edges.isEmpty()) {

            Edge edge = edges.poll();

            int rootA = find(parent, edge.from);
            int rootB = find(parent, edge.to);

            if (rootA == rootB) {
                continue;
            }

            parent[rootB] = rootA;

            totalCost += edge.cost;

            if (++connected == groupCount - 1) {
                break;
            }
        }

        return totalCost;
    }

    private int find(int[] parent, int node) {

        if (parent[node] == node) {
            return node;
        }

        return parent[node] =
                find(parent, parent[node]);
    }

    private boolean isValid(int x, int y, int n) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }
}
