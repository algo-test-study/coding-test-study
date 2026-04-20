import java.util.Arrays;
import java.util.Stack;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = {};
        Stack<Integer[]> stack = new Stack<>();

        answer = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            if (stack.isEmpty()) {
                stack.push(new Integer[]{i, prices[i]});
                continue;
            }

            while (!stack.isEmpty()) {
                Integer[] peek = stack.peek();
                if (peek[1] > prices[i]) {
                    stack.pop();

                    answer[peek[0]] = i - peek[0];
                } else {
                    break;
                }
            }

            stack.push(new Integer[]{i, prices[i]});
        }

        while (!stack.isEmpty()) {
            Integer[] pop = stack.pop();

            answer[pop[0]] = prices.length - pop[0] - 1;
        }

        return answer;
    }
}
