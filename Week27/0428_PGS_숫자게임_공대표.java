import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);

        int i = 0;
        int j = 0;

        while (i < A.length && j < B.length) {
            if (B[j] > A[i]) {
                answer++;
                i++;
                j++;
            } else {
                j++;
            }
        }

        return answer;
    }
}
