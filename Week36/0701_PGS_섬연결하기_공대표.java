import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int answer = 0;
        int count = 0;

        for (int[] cost : costs) {
            int a = find(parent, cost[0]);
            int b = find(parent, cost[1]);

            if (a != b) {
                parent[b] = a;
                answer += cost[2];
                
                count++;
                if (count == n - 1) {
                    break;
                }
            }
        }

        return answer;
    }

    private int find(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent, parent[x]);
    }
}
