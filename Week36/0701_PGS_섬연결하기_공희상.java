import java.util.Arrays;

class Solution {

    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));

        UnionFind unionFind = new UnionFind(n);

        int totalCost = 0;
        int selectedEdgeCount = 0;

        for (int[] cost : costs) {
            int islandA = cost[0];
            int islandB = cost[1];
            int bridgeCost = cost[2];

            if (unionFind.find(islandA) == unionFind.find(islandB)) continue;

            unionFind.union(islandA, islandB);

            totalCost += bridgeCost;
            selectedEdgeCount++;

            if (selectedEdgeCount == n - 1) break;
        }

        return totalCost;
    }

    private static class UnionFind {

        private final int[] parent;
        private final int[] rank;

        private UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];

            for (int node = 0; node < size; node++) {
                parent[node] = node;
            }
        }

        private int find(int node) {
            if (parent[node] != node) {
                parent[node] = find(parent[node]);
            }

            return parent[node];
        }

        private void union(int nodeA, int nodeB) {
            int rootA = find(nodeA);
            int rootB = find(nodeB);

            if (rootA == rootB) {
                return;
            }

            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
                return;
            }

            if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
                return;
            }

            parent[rootB] = rootA;
            rank[rootA]++;
        }
    }
}
