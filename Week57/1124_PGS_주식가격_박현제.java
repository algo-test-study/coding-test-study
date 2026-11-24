import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        
        int[] ans = new int[n];
        
        Stack<Integer> stk = new Stack<>();
        
        for(int i = 0; i < n; i++){
            while(!stk.isEmpty() && prices[stk.peek()] > prices[i]){
                int idx = stk.pop();
                ans[idx] = i - idx;
            }
          
            stk.push(i);
        }
        
        while(!stk.isEmpty()){
            int idx = stk.pop();
            ans[idx] = n - 1 - idx;
        }
        
        return ans;
    }
}
