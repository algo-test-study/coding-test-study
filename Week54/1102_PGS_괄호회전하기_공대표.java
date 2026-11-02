
import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            Stack<Character> stack = new Stack<>();

            boolean isValid = true;
            for (int j = 0; j < s.length(); j++) {
                char current = s.charAt(j);

                if (current == '(' || current == '[' || current == '{') {
                    stack.push(current);
                } else {
                    if (stack.isEmpty()) {
                        isValid = false;
                        break;
                    }

                    char top = stack.pop();

                    if ((current == ')' && top != '(') ||
                        (current == ']' && top != '[') ||
                        (current == '}' && top != '{')) {
                        isValid = false;
                        break;
                    }
                }
            }

            if (isValid && stack.isEmpty()) {
                answer++;
            }

            s = s.substring(1) + s.charAt(0);
        }

        return answer;
    }
}
