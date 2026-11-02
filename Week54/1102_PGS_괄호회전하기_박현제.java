import java.util.*;
class Solution {
    public int solution(String s) {
        Queue<Character> strs = new ArrayDeque<>();
        
        for(char curr : s.toCharArray()){
            strs.offer(curr);
        }
        
        int x = s.length();
        int cnt = 0;
        
        for(int i = 0; i < x; i++){
            Deque<Character> stk = new ArrayDeque<>();
            for(char curr : strs){
                if(!stk.isEmpty()){
                    if((stk.peek() == '[' && curr == ']') || (stk.peek() == '(' && curr == ')') || (stk.peek() == '{' && curr == '}')){
                        stk.pop();
                        continue;
                    }   
                }
                stk.push(curr);
            }
            if(stk.isEmpty()) cnt++;
            strs.offer(strs.poll());
        }
        
        return cnt;
    }
}
