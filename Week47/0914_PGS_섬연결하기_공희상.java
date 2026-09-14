import java.util.Arrays;

class Solution {

    private int[] parent;

    public int solution(int n, int[][] costs) {
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        Arrays.sort(costs, (a, b) -> a[2] - b[2]);

        int answer = 0;
        int count = 0;

        for (int[] cost : costs) {
            int from = cost[0];
            int to = cost[1];
            int value = cost[2];

            if (find(from) != find(to)) {
                union(from, to);

                answer += value;
                count++;

                if (count == n - 1) {
                    break;
                }
            }
        }

        return answer;
    }

    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    private void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }
}
