import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {

    private List<Edge>[] graph;
    private int n;
    private int k;
    private int answer;

    public int solution(int n, int infection, int[][] edges, int k) {
        this.n = n;
        this.k = k;
        this.answer = 1;

        graph = new ArrayList[n + 1];

        for (int node = 1; node <= n; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int type = edge[2];

            graph[from].add(new Edge(to, type));
            graph[to].add(new Edge(from, type));
        }

        boolean[] infected = new boolean[n + 1];
        infected[infection] = true;

        dfs(0, 0, infected, 1);

        return answer;
    }

    private void dfs(
            int depth,
            int lastType,
            boolean[] infected,
            int infectedCount
    ) {
        answer = Math.max(answer, infectedCount);

        if (depth == k || answer == n) {
            return;
        }

        for (int type = 1; type <= 3; type++) {
            if (type == lastType) {
                continue;
            }

            boolean[] nextInfected = infected.clone();

            int nextCount = spread(
                    nextInfected,
                    infectedCount,
                    type
            );

            dfs(
                    depth + 1,
                    type,
                    nextInfected,
                    nextCount
            );

            if (answer == n) {
                return;
            }
        }
    }

    private int spread(
            boolean[] infected,
            int infectedCount,
            int selectedType
    ) {
        Queue<Integer> queue = new ArrayDeque<>();

        for (int node = 1; node <= n; node++) {
            if (infected[node]) {
                queue.offer(node);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Edge edge : graph[current]) {
                if (edge.type != selectedType) {
                    continue;
                }

                if (infected[edge.to]) {
                    continue;
                }

                infected[edge.to] = true;
                infectedCount++;
                queue.offer(edge.to);
            }
        }

        return infectedCount;
    }

    private static class Edge {

        private final int to;
        private final int type;

        private Edge(int to, int type) {
            this.to = to;
            this.type = type;
        }
    }
}
