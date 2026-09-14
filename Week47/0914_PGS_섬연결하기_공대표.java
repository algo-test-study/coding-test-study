import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);

        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int answer = 0;
        int bridgeCount = 0;

        for (int[] cost : costs) {
            int island1 = cost[0];
            int island2 = cost[1];
            int bridgeCost = cost[2];

            int parent1 = find(parent, island1);
            int parent2 = find(parent, island2);

            if (parent1 == parent2) {
                continue;
            }

            parent[parent2] = parent1;

            answer += bridgeCost;
            bridgeCount++;

            if (bridgeCount == n - 1) {
                break;
            }
        }

        return answer;
    }

    private int find(int[] parent, int island) {
        if (parent[island] == island) {
            return island;
        }

        return parent[island] = find(parent, parent[island]);
    }
}
