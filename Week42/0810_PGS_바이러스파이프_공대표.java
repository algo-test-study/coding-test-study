import java.util.*;

class Solution {
    public int solution(int n, int infection, int[][] edges, int k) {
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int from = edge[0] - 1;
            int to = edge[1] - 1;
            int type = edge[2];

            graph[from].add(new int[]{to, type});
            graph[to].add(new int[]{from, type});
        }

        int totalCase = 1;
        for (int i = 0; i < k; i++) {
            totalCase *= 3;
        }

        int answer = 1;

        for (int caseNumber = 0; caseNumber < totalCase; caseNumber++) {
            boolean[] infected = new boolean[n];
            infected[infection - 1] = true;

            int sequence = caseNumber;
            
            for (int action = 0; action < k; action++) {
                int type = sequence % 3 + 1;
                sequence /= 3;

                Queue<Integer> queue = new LinkedList<>();
                boolean[] visited = new boolean[n];

                for (int i = 0; i < n; i++) {
                    if (infected[i]) {
                        queue.offer(i);
                        visited[i] = true;
                    }
                }

                while (!queue.isEmpty()) {
                    int current = queue.poll();

                    for (int[] next : graph[current]) {
                        int nextNode = next[0];
                        int nextType = next[1];

                        if (nextType != type) {
                            continue;
                        }

                        if (visited[nextNode]) {
                            continue;
                        }

                        visited[nextNode] = true;
                        infected[nextNode] = true;
                        queue.offer(nextNode);
                    }
                }
            }

            int count = 0;

            for (int i = 0; i < n; i++) {
                if (infected[i]) {
                    count++;
                }
            }

            answer = Math.max(answer, count);
        }

        return answer;
    }
}
