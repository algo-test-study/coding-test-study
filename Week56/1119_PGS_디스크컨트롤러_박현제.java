import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int n = jobs.length;
        int total = 0;

        Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        int prevEnd = 0;
        int i = 0;
        int cnt = 0;

        while (cnt < n) {

            while (i < n && jobs[i][0] <= prevEnd) {
                int start = jobs[i][0];
                int ran = jobs[i][1];

                pq.offer(new int[]{ran, start});
                i++;
            }

            if (!pq.isEmpty()) {
                int[] curr = pq.poll();

                int currRan = curr[0];
                int currStart = curr[1];

                total += prevEnd - currStart + currRan;
                prevEnd += currRan;
                cnt++;

            } else {
                prevEnd = jobs[i][0];
            }
        }

        return total / n;
    }
}
