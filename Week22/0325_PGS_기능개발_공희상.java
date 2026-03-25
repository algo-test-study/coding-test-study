import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < progresses.length; i++) {
            int remain = 100 - progresses[i];
            int day = (remain + speeds[i] - 1) / speeds[i];
            q.offer(day);
        }

        List<Integer> answer = new ArrayList<>();

        while (!q.isEmpty()) {
            int current = q.poll();
            int count = 1;

            while (!q.isEmpty() && q.peek() <= current) {
                q.poll();
                count++;
            }

            answer.add(count);
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
