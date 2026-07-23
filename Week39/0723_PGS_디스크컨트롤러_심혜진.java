import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> {
            if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) {
                if (a[0] == b[0]) return Integer.compare(a[2], b[2]);
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int n = jobs.length;
        int idx = 0;
        int time = 0;
        int totalTurnaround = 0;
        int jobId = 0;

        while (idx < n || !pq.isEmpty()) {
            while (idx < n && jobs[idx][0] <= time) {
                pq.offer(new int[]{jobs[idx][0], jobs[idx][1], jobId++});
                idx++;
            }

            if (pq.isEmpty()) {
                time = jobs[idx][0];
                continue;
            }

            int[] job = pq.poll();
            int requestTime = job[0];
            int duration = job[1];

            time += duration;
            totalTurnaround += time - requestTime;
        }

        return totalTurnaround / n;
    }
}
