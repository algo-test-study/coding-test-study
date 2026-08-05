import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class FreqStack {
    Map<Integer, Integer> count;
    Map<Integer, Stack<Integer>> stack;
    int maxCount;

    public FreqStack() {
        count = new HashMap<>();
        stack = new HashMap<>();
        maxCount = 0;
    }

    public void push(int val) {
        int currentCount = count.getOrDefault(val, 0) + 1;
        count.put(val, currentCount);

        if (!stack.containsKey(currentCount)) {
            stack.put(currentCount, new Stack<>());
        }

        stack.get(currentCount).push(val);

        maxCount = Math.max(maxCount, currentCount);
    }

    public int pop() {
        int value = stack.get(maxCount).pop();
        count.put(value, count.get(value) - 1);

        if (stack.get(maxCount).isEmpty()) {
            maxCount--;
        }

        return value;
    }
}
