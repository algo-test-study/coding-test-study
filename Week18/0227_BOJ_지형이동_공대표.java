import java.util.*;

class Solution {

    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public int solution(int[][] land, int height) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
      
        int N = land.length;
        int[][] group = new int[N][N];
        int groupId = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (group[i][j] == 0) {
                    groupId++;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});
                    group[i][j] = groupId;

                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        int x = cur[0];
                        int y = cur[1];

                        for (int d = 0; d < 4; d++) {
                            int nx = x + dx[d];
                            int ny = y + dy[d];

                            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                                if (group[nx][ny] == 0 && Math.abs(land[x][y] - land[nx][ny]) <= height) {
                                    group[nx][ny] = groupId;
                                    q.offer(new int[]{nx, ny});
                                }
                            }
                        }
                    }
                }
            }
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                        if (group[x][y] != group[nx][ny]) {
                            int cost = Math.abs(land[x][y] - land[nx][ny]);
                            pq.offer(new Edge(group[x][y], group[nx][ny], cost));
                        }
                    }
                }
            }
        }

        int[] parent = new int[groupId + 1];
      
        for (int i = 1; i <= groupId; i++) parent[i] = i;

        int answer = 0;
        int count = 0;

        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            int a = find(parent, e.from);
            int b = find(parent, e.to);

            if (a != b) {
                parent[b] = a;
                answer += e.cost;
                count++;
              
                if (count == groupId - 1) break;
            }
        }

        return answer;
    }

    private int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent, parent[x]);
    }
}
