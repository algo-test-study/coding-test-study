/**
1. 완전탐색, O(n^2)
2. 스택, O(n)
**/
// import java.util.*;
// class Solution {
//     public int[] solution(int[] prices) {
        
//         int n = prices.length;
        
//         int[] answer = new int[n];

//         for(int i=0; i<n-1; i++){
//             for(int j=i+1; j<n; j++){
//                 answer[i]++;
//                 if(prices[i] > prices[j]){
//                     break;
//                 }
//             }
            
//         }
    
//         return answer;
//     }
// }

import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        
        int n = prices.length;
        
        int[] answer = new int[n];
        
        Stack<Integer> stk = new Stack<>();
        
        for(int i=0; i<n ; i++){
            while(!stk.isEmpty() && prices[stk.peek()] > prices[i]){
                int idx = stk.pop();
                answer[idx] = i - idx;
            }
            stk.push(i);
        }
        
        while(!stk.isEmpty()){
            int idx = stk.pop();
            answer[idx] = n-1 - idx;
        }
        
    
        return answer;
    }
}
