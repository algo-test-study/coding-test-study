import java.util.*;

class Solution {

    public int[] solution(int n, int[][] paths,
                          int[] gates, int[] summits) {

        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            int a = path[0];
            int b = path[1];
            int weight = path[2];

            graph.get(a).add(new int[]{b, weight});
            graph.get(b).add(new int[]{a, weight});
        }

        boolean[] isGate = new boolean[n + 1];
        boolean[] isSummit = new boolean[n + 1];

        for (int gate : gates) {
            isGate[gate] = true;
        }

        for (int summit : summits) {
            isSummit[summit] = true;
        }

        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            Comparator.comparingInt(a -> a[1])
        );

        for (int gate : gates) {
            intensity[gate] = 0;
            pq.offer(new int[]{gate, 0});
        }

        while (!pq.isEmpty()) {
            int[] current = pq.poll();

            int node = current[0];
            int cost = current[1];

            if (cost > intensity[node]) {
                continue;
            }

            if (isSummit[node]) {
                continue;
            }

            for (int[] edge : graph.get(node)) {
                int next = edge[0];
                int weight = edge[1];

                if (isGate[next]) {
                    continue;
                }

                int nextIntensity = Math.max(cost, weight);

                if (nextIntensity < intensity[next]) {
                    intensity[next] = nextIntensity;
                    pq.offer(new int[]{next, nextIntensity});
                }
            }
        }

        int bestSummit = Integer.MAX_VALUE;
        int bestIntensity = Integer.MAX_VALUE;

        for (int summit : summits) {
            int cost = intensity[summit];

            if (cost < bestIntensity ||
                (cost == bestIntensity && summit < bestSummit)) {

                bestSummit = summit;
                bestIntensity = cost;
            }
        }

        return new int[]{bestSummit, bestIntensity};
    }
}
