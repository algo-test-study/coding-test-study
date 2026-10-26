import java.util.Arrays;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1]));

        int answer = 0;
        int lastPosition = -1;

        for (int[] target : targets) {
            if (lastPosition <= target[0]) {
                answer++;
                lastPosition = target[1];
            }
        }

        return answer;
    }
}
