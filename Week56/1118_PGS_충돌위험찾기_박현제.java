import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int n = routes.length;
        int ans = 0;

        int[] x = new int[n];
        int[] y = new int[n];
        int[] nxt = new int[n];
        boolean[] end = new boolean[n];

        int cnt = 0;
        for (int i = 0; i < n;i++) {
            int start = routes[i][0] - 1;

            x[i] = points[start][0];
            y[i] = points[start][1];
            nxt[i] = 1;
        }

        while (cnt < n) {

            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < n; i++) {
                if (end[i]) continue;

                int key = x[i] * 101 + y[i];

                int currCnt = map.getOrDefault(key, 0) + 1;
                map.put(key, currCnt);
                
                if (currCnt == 2) {
                    ans++;
                }
            }
            for (int i = 0; i < n;i++) {
                if (end[i]) continue;

                if (nxt[i] == routes[i].length) {
                    end[i] = true;
                    cnt++;
                    continue;
                }

                int curr = routes[i][nxt[i]] - 1;

                int ex = points[curr][0];
                int ey = points[curr][1];

                if (x[i] != ex) {
                    if (x[i] < ex) {
                        x[i]++;
                    } else {
                        x[i]--;
                    }
                } else if (y[i] != ey) {
                    if (y[i] < ey) {
                        y[i]++;
                    } else {
                        y[i]--;
                    }
                }

                if (x[i] == ex && y[i] == ey) {
                    nxt[i]++;
                }
            }
        }

        return ans;
    }
}
