import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class FreqStack {

    private final Map<Integer, Integer> frequencies;
    private final Map<Integer, Deque<Integer>> frequencyStacks;
    
    private int maxFrequency;

    public FreqStack() {
        frequencies = new HashMap<>();
        frequencyStacks = new HashMap<>();
        maxFrequency = 0;
    }

    public void push(int val) {
        int frequency = frequencies.getOrDefault(val, 0) + 1;
        frequencies.put(val, frequency);

        frequencyStacks
                .computeIfAbsent(frequency, key -> new ArrayDeque<>())
                .push(val);

        maxFrequency = Math.max(maxFrequency, frequency);
    }

    public int pop() {
        Deque<Integer> stack = frequencyStacks.get(maxFrequency);
        int val = stack.pop();

        frequencies.put(val, frequencies.get(val) - 1);

        if (stack.isEmpty()) {
            frequencyStacks.remove(maxFrequency);
            maxFrequency--;
        }

        return val;
    }
}
