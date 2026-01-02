import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(String dirs) {
        int x = 0, y = 0;
        int answer = 0;

        Set<String> visited = new HashSet<>();

        for (char dir : dirs.toCharArray()) {
            int nx = x;
            int ny = y;

            switch (dir) {
                case 'U': ny++; break;
                case 'D': ny--; break;
                case 'R': nx++; break;
                case 'L': nx--; break;
            }

            if (nx < -5 || nx > 5 || ny < -5 || ny > 5) {
                continue;
            }

            String path1 = x + "," + y + "->" + nx + "," + ny;
            String path2 = nx + "," + ny + "->" + x + "," + y;

            if (!visited.contains(path1)) {
                visited.add(path1);
                visited.add(path2);
                answer++;
            }

            x = nx;
            y = ny;
        }

        return answer;
    }
}
