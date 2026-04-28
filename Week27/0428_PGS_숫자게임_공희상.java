import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        int answer = 0;
        int index = 0;
        for (int b : B) {
            if (b > A[index]) {
                answer++;
                index++;
            }
        }
        return answer;
    }
}
