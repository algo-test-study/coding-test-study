import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    static class Edge {
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static class DisjointSet {
        int[] parent;

        DisjointSet(int size) {
            parent = new int[size];

            for (int index = 0; index < size; index++) {
                parent[index] = index;
            }
        }

        int find(int node) {
            if (parent[node] == node) {
                return node;
            }

            parent[node] = find(parent[node]);
            return parent[node];
        }

        boolean union(int firstNode, int secondNode) {
            int firstRoot = find(firstNode);
            int secondRoot = find(secondNode);

            if (firstRoot == secondRoot) {
                return false;
            }

            parent[secondRoot] = firstRoot;
            return true;
        }
    }

    public int solution(int[][] land, int height) {
        int landSize = land.length;
        int totalNodeCount = landSize * landSize;

        List<Edge> edges = new ArrayList<>();

        int[] moveY = {1, 0};
        int[] moveX = {0, 1};

        for (int y = 0; y < landSize; y++) {
            for (int x = 0; x < landSize; x++) {
                int currentNode = y * landSize + x;

                for (int direction = 0; direction < 2; direction++) {
                    int nextY = y + moveY[direction];
                    int nextX = x + moveX[direction];

                    if (nextY >= landSize || nextX >= landSize) {
                        continue;
                    }

                    int nextNode = nextY * landSize + nextX;
                    int difference = Math.abs(land[y][x] - land[nextY][nextX]);

                    int cost = 0;

                    if (difference > height) {
                        cost = difference;
                    }

                    edges.add(new Edge(currentNode, nextNode, cost));
                }
            }
        }

        Collections.sort(edges, (firstEdge, secondEdge) ->
                Integer.compare(firstEdge.cost, secondEdge.cost)
        );

        DisjointSet disjointSet = new DisjointSet(totalNodeCount);

        int answer = 0;
        int connectedEdgeCount = 0;

        for (Edge edge : edges) {
            if (disjointSet.union(edge.from, edge.to)) {
                answer += edge.cost;
                connectedEdgeCount++;

                if (connectedEdgeCount == totalNodeCount - 1) {
                    break;
                }
            }
        }

        return answer;
    }
}
