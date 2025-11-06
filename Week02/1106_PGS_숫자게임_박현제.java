/*
알고리즘
- a, b 정렬
- a<b인 값을 차례로 대응시킴

시간복잡도
- O(N)
*/

import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        int score = 0;
        int n = A.length;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int i=0;
        int j=0;
        while(j < n){
            if(A[i]<B[j]){
                score++;
                i++;
                j++;
            }
            else{
                j++;
            } 
        }
        return score;
    }
}
