import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        int answer = 0;

        long total = 0;
        for (int t : food_times) total += t;
        
        if (total <= k) return -1;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int i = 0; i < food_times.length; i++) {
            pq.add(new int[]{food_times[i], i + 1});
        }

        long prev = 0;
        long n = food_times.length;

        while (true) {
            long now = pq.peek()[0];
            long diff = now - prev;
            long spend = diff * n;

            if (k >= spend) {
                k -= spend;
                prev = now;

                while (!pq.isEmpty() && pq.peek()[0] == now) {
                    pq.poll();
                    n--;
                }
            } else {
                List<int[]> list = new ArrayList<>(pq);
                list.sort((a, b) -> a[1] - b[1]);
                answer = list.get((int)(k % n))[1];
                
                break;
            }
        }

        return answer;
    }
}
