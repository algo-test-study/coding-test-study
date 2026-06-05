import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<int[]> q = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < priorities.length; i++) {
            q.offer(new int[]{i, priorities[i]});
            pq.offer(priorities[i]);
        }
        
        int count = 0;
        while (!q.isEmpty()) {
            int[] current = q.poll();
            int currentIndex = current[0];
            int currentPriority = current[1];
            
            if (currentPriority == pq.peek()) {
                count++;
                pq.poll();
                if (currentIndex == location) {
                    return count;
                }
            } else {
                q.offer(current);
            }
        }
        return count;
    }
}
