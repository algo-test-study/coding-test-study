import java.util.*;

class Solution {
    static class Edge {
        int from, to, cost;
        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    int[] parent;

    int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    boolean union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) return false;
        parent[rb] = ra;
        return true;
    }

    public int solution(int n, int[][] costs) {
        List<Edge> edges = new ArrayList<>();
        for (int[] c : costs) {
            edges.add(new Edge(c[0], c[1], c[2]));
        }

        edges.sort(Comparator.comparingInt(e -> e.cost));

        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        int answer = 0;
        int used = 0;

        for (Edge e : edges) {
            if (union(e.from, e.to)) {
                answer += e.cost;
                used++;
                if (used == n - 1) break;
            }
        }

        return answer;
    }
}
