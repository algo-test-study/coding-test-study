import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int cnt = 0;
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        
        int n = topping.length;
        
        for(int curr : topping){
            right.put(curr, right.getOrDefault(curr, 0) + 1);
        }
        
        for(int i = 0; i < n - 1; i++){
            int curr = topping[i];
            left.put(curr, left.getOrDefault(curr, 0) + 1);
            
            right.put(curr, right.getOrDefault(curr, 0) - 1);
            
            if(right.get(curr) == 0) right.remove(curr);
            
            if(right.size() == left.size()) cnt++;
        }
        
        
        return cnt;
    }
}
