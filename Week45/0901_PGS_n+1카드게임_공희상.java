class Solution {

    public int[] solution(int[][] edges) {
        int maxNode = 0;

        for (int[] edge : edges) {
            maxNode = Math.max(maxNode, Math.max(edge[0], edge[1]));
        }

        int[] inDegree = new int[maxNode + 1];
        int[] outDegree = new int[maxNode + 1];

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            outDegree[from]++;
            inDegree[to]++;
        }

        int createdNode = 0;
        int stickCount = 0;
        int eightCount = 0;

        for (int node = 1; node <= maxNode; node++) {

            if (inDegree[node] == 0 && outDegree[node] >= 2) {
                createdNode = node;
            }

            if (inDegree[node] >= 1 && outDegree[node] == 0) {
                stickCount++;
            }

            if (inDegree[node] >= 2 && outDegree[node] == 2) {
                eightCount++;
            }
        }

        int totalGraphCount = outDegree[createdNode];

        int donutCount = totalGraphCount - stickCount - eightCount;

        return new int[]{
                createdNode,
                donutCount,
                stickCount,
                eightCount
        };
    }
}
