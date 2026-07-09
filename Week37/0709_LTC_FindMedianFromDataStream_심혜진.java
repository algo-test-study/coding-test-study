import java.util.PriorityQueue;
import java.util.Collections;

class MedianFinder {

    private PriorityQueue<Integer> lower;
    private PriorityQueue<Integer> higher;

    public MedianFinder() {
        lower = new PriorityQueue<>(Collections.reverseOrder());
        higher = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (lower.isEmpty() || num <= lower.peek()) {
            lower.offer(num);
        } else {
            higher.offer(num);
        }

        if (lower.size() > higher.size() + 1) {
            higher.offer(lower.poll());
        } else if (higher.size() > lower.size()) {
            lower.offer(higher.poll());
        }
    }

    public double findMedian() {
        if (lower.size() > higher.size()) {
            return lower.peek();
        } else {
            return (lower.peek() + higher.peek()) / 2.0;
        }
    }
}
