class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[] dist = {
            getDist(p1, p2),
            getDist(p1, p3),
            getDist(p1, p4),
            getDist(p2, p3),
            getDist(p2, p4),
            getDist(p3, p4)
        };

        Arrays.sort(dist);

        return dist[0] > 0 &&
               dist[0] == dist[1] &&
               dist[1] == dist[2] &&
               dist[2] == dist[3] &&
               dist[4] == dist[5] &&
               dist[4] == 2 * dist[0];
    }

    private int getDist(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];
        return dx * dx + dy * dy;
    }
}