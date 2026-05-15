import java.util.HashSet;
import java.util.Set;

class Solution {
    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};
    private static final char[] dir = {'U', 'D', 'R', 'L'};

    public int solution(String dirs) {
        int x = 0;
        int y = 0;

        Set<String> set = new HashSet<>();

        for (char d : dirs.toCharArray()) {
            int nx = x;
            int ny = y;

            for (int i = 0; i < 4; i++) {
                if (d == dir[i]) {
                    nx += dx[i];
                    ny += dy[i];
                }
            }

            if (nx < -5 || nx > 5 || ny < -5 || ny > 5) {
                continue;
            }

            String path = x + "," + y + ":" + nx + "," + ny;
            String reverse = nx + "," + ny + ":" + x + "," + y;

            if (!set.contains(path)) {
                set.add(path);
                set.add(reverse);
            }

            x = nx;
            y = ny;
        }

        return set.size() / 2;
    }
}
