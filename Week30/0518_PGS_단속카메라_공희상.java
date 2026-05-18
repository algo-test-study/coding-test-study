import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (a, b) -> {
            return a[1] - b[1];
        });
        
        int camera = -30001;
        
        for (int[] route: routes) {
            int in = route[0];
            int out = route[1];
            
            if (camera < in) {
                camera = out;
                answer++;
            }
        }
        return answer;
    }
}
