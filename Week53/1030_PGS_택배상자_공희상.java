import java.util.*;

class Solution {

    public int solution(int[] order) {

        Deque<Integer> stack = new ArrayDeque<>();
        int nextBox = 1;
        int answer = 0;
        for (int target : order) {
            while (nextBox < target) {
                stack.push(nextBox);
                nextBox++;
            }
            if (nextBox == target) {
                nextBox++;
                answer++;
            }
            else if (!stack.isEmpty()
                    && stack.peek() == target) {

                stack.pop();
                answer++;
            }
            else {
                break;
            }
        }

        return answer;
    }
}
