import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        int answer = 0;
        int bIndex = 0;
        
        for (int a : A) {
            while (bIndex < B.length && B[bIndex] <= a) {
                bIndex++;
            }
          
            if (bIndex < B.length) {
                answer++;
                bIndex++;
            }
        }
        
        return answer;
    }
}
