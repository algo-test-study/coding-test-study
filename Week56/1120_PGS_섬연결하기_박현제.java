import java.util.*;
class Solution {
    static int[] root;
    public int solution(int n, int[][] costs) {
        int answer = 0;

        root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }

        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));

        for (int[] cost : costs) {
            int a = cost[0];
            int b = cost[1];
            int w = cost[2];

            if (find(a) != find(b)) {
                answer += w;
                union(a, b);
            }
        }

        return answer;
    }

    static int find(int x) {
        if (root[x] == x) {
            return x;
        }

        root[x] = find(root[x]);
        return root[x];
    }

    static void union(int x, int y) {
        int a = find(x);
        int b = find(y);

        root[b] = a;
    }
}
