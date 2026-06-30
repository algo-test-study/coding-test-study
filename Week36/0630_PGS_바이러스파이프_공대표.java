import java.util.*;

class Solution {
    static class Edge {
        int to;
        int type;

        Edge(int to, int type) {
            this.to = to;
            this.type = type;
        }
    }

    List<Edge>[] graph;
    int answer = 0;

    public int solution(int n, int infection, int[][] edges, int k) {

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(new Edge(edge[1], edge[2]));
            graph[edge[1]].add(new Edge(edge[0], edge[2]));
        }

        boolean[] infected = new boolean[n + 1];
        infected[infection] = true;

        dfs(infected, 0, k);

        return answer;
    }

    private void dfs(boolean[] infected, int depth, int k) {
        int cnt = 0;
        
        for (int i = 1; i < infected.length; i++) {
            if (infected[i]) cnt++;
        }
        answer = Math.max(answer, cnt);

        if (depth == k) {
            return;
        }

        for (int type = 1; type <= 3; type++) {
            boolean[] next = infected.clone();

            Queue<Integer> queue = new LinkedList<>();

            for (int i = 1; i < next.length; i++) {
                if (next[i]) {
                    queue.offer(i);
                }
            }

            while (!queue.isEmpty()) {
                int cur = queue.poll();

                for (Edge edge : graph[cur]) {
                    if (edge.type != type) continue;
                    if (next[edge.to]) continue;

                    next[edge.to] = true;
                    queue.offer(edge.to);
                }
            }

            dfs(next, depth + 1, k);
        }
    }
}
