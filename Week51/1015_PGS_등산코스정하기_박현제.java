import java.util.*;
class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        Set<Integer> endSet = new HashSet<>();
        Set<Integer> startSet = new HashSet<>();

        for (int summit : summits) endSet.add(summit);
        for (int gate : gates) startSet.add(gate);

        List<int[]>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }

        for (int[] path : paths) {
            int a = path[0];
            int b = path[1];
            int c = path[2];

            g[a].add(new int[]{b, c});
            g[b].add(new int[]{a, c});
        }

        int minSummit = Integer.MAX_VALUE;
        int minIntensity = Integer.MAX_VALUE;

        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        int[] intensities = new int[n + 1];
        Arrays.fill(intensities, Integer.MAX_VALUE);

        for (int start : gates) {
            pq.offer(new int[]{start, 0});
            intensities[start] = 0;
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int cx = cur[0];
            int cw = cur[1];

            if (cw > intensities[cx]) continue;
            if (endSet.contains(cx)) continue;

            for (int[] next : g[cx]) {
                int nx = next[0];
                int nw = next[1];

                if (startSet.contains(nx)) continue;

                nw = Math.max(cw, nw);

                if (intensities[nx] > nw) {
                    intensities[nx] = nw;
                    pq.offer(new int[]{nx, nw});
                }
            }
        }

        Arrays.sort(summits);

        for (int end : summits) {
            if (intensities[end] < minIntensity) {
                minIntensity = intensities[end];
                minSummit = end;
            }
        }

        return new int[]{minSummit, minIntensity};
    }
}
