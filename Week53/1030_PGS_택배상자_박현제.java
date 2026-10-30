import java.util.*;
class Solution {
    public int solution(int[] order) {
        Deque<Integer> stk = new ArrayDeque<>();

        int n = order.length;
        int idx = 0;

        for (int i = 1; i <= n; i++) {
            stk.push(i);

            while (!stk.isEmpty() && stk.peek() == order[idx]) {
                stk.pop();
                idx++;

                if (idx == n) {
                    return idx;
                }
            }
        }

        return idx;
    }
}
