class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        StringBuilder answer = new StringBuilder();

        int dist = Math.abs(x - r) + Math.abs(y - c);

        if (dist > k || (k - dist) % 2 != 0) {
            return "impossible";
        }

        int[] dx = {1, 0, 0, -1}; // d, l, r, u
        int[] dy = {0, -1, 1, 0};
        char[] dir = {'d', 'l', 'r', 'u'};

        for (int i = 0; i < k; i++) {
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 1 || ny < 1 || nx > n || ny > m) continue;

                int remain = k - i - 1;
                int newDist = Math.abs(nx - r) + Math.abs(ny - c);

                if (newDist <= remain) {
                    answer.append(dir[d]);
                    x = nx;
                    y = ny;
                    break;
                }
            }
        }

        return answer.toString();
    }
}
