
import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        Stack<Integer> stack = new Stack<>();

        int answer = 0;
        int box = 1;

        for (int i = 0; i < order.length; i++) {
            while (box <= order.length && box <= order[i]) {
                stack.push(box);
                box++;
            }

            if (!stack.isEmpty() && stack.peek() == order[i]) {
                stack.pop();
                answer++;
            } else {
                break;
            }
        }

        return answer;
    }
}
