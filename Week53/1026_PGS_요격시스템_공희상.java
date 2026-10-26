import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, Comparator.comparingInt(a -> a[1]));
        int answer = 0;
        int lastEnd = -1;
        for (int[] target : targets) {
            int start = target[0];
            int end = target[1];
            if (start >= lastEnd) {
                answer++;
                lastEnd = end;
            }
        }
        return answer;
    }
}
