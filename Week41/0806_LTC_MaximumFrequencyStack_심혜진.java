import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class FreqStack {

    private Map<Integer, Integer> freq;
    private Map<Integer, Stack<Integer>> group;
    private int maxFreq;

    public FreqStack() {
        freq = new HashMap<>();
        group = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        int f = freq.getOrDefault(val, 0) + 1;
        freq.put(val, f);

        if (f > maxFreq) {
            maxFreq = f;
        }

        group.computeIfAbsent(f, k -> new Stack<>()).push(val);
    }

    public int pop() {
        Stack<Integer> stack = group.get(maxFreq);
        int val = stack.pop();

        freq.put(val, freq.get(val) - 1);
        if (stack.isEmpty()) {
            group.remove(maxFreq);
            maxFreq--;
        }

        return val;
    }
}
