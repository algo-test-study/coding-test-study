import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        Map<Integer, List<Integer>> g = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            g.put(i, new ArrayList<>());
        }
        
        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            
            g.get(a).add(b);
            g.get(b).add(a);
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        Deque<Integer> q = new ArrayDeque<>();
        q.offer(destination);
        dist[destination] = 0;

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int nxt : g.getOrDefault(curr, new ArrayList<>())) {
                if (dist[nxt] != -1) {
                    continue;
                }

                dist[nxt] = dist[curr] + 1;
                q.offer(nxt);
            }
        }

        int[] answer = new int[sources.length];

        for (int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]];
        }

        return answer;
    }
}
