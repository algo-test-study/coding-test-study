import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();

        int result = 0;
        int number = 0;
        int sign = 1;

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (Character.isDigit(current)) {
                number = number * 10 + (current - '0');
            } else if (current == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (current == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (current == '(') {
                stack.push(result);
                stack.push(sign);

                result = 0;
                number = 0;
                sign = 1;
            } else if (current == ')') {
                result += sign * number;
                number = 0;

                int previousSign = stack.pop();
                int previousResult = stack.pop();

                result = previousResult + previousSign * result;
            }
        }

        result += sign * number;

        return result;
    }
}
