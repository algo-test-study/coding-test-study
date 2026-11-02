import java.util.*;

class Solution {
    public int solution(String s) {
        int n = s.length();
        int answer = 0;
        
        for (int i = 0; i < n; i++) {
            if (isRight(s, i)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private boolean isRight(String s, int start) {
        Deque<Character> stack = new ArrayDeque<>();
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt((start + i) % n);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
                continue;
            }

            if (stack.isEmpty()) {
                return false;
            }

            char open = stack.pop();

            if (c == ')' && open != '(') {
                return false;
            }

            if (c == ']' && open != '[') {
                return false;
            }

            if (c == '}' && open != '{') {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
