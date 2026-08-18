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
            int a = cost[0];
            int b = cost[1];
            int price = cost[2];

            int rootA = a;
            int rootB = b;

            while (parent[rootA] != rootA) {
                rootA = parent[rootA];
            }

            while (parent[rootB] != rootB) {
                rootB = parent[rootB];
            }

            if (rootA == rootB) {
                continue;
            }
            
            parent[rootB] = rootA;
            
            answer += price;
            count++;
            
            if (count == n - 1) {
                break;
            }
        }

        return answer;
    }
}
