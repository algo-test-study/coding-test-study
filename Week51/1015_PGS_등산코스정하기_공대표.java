import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] path : paths) {
            graph[path[0]].add(new int[]{path[1], path[2]});
            graph[path[1]].add(new int[]{path[0], path[2]});
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

        PriorityQueue<int[]> queue = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[1], b[1])
        );

        for (int gate : gates) {
            intensity[gate] = 0;
            queue.offer(new int[]{gate, 0});
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int cost = current[1];

            if (cost > intensity[node]) {
                continue;
            }

            if (isSummit[node]) {
                continue;
            }

            for (int[] next : graph[node]) {
                int nextNode = next[0];
                int nextCost = Math.max(cost, next[1]);

                if (intensity[nextNode] > nextCost) {
                    intensity[nextNode] = nextCost;
                    queue.offer(new int[]{nextNode, nextCost});
                }
            }
        }

        Arrays.sort(summits);

        int bestSummit = 0;
        int minIntensity = Integer.MAX_VALUE;

        for (int summit : summits) {
            if (intensity[summit] < minIntensity) {
                minIntensity = intensity[summit];
                bestSummit = summit;
            }
        }

        return new int[]{bestSummit, minIntensity};
    }
}
