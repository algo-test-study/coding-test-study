import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); // 정렬 큐

        int excuteCnt = 0;
        int sec = 0; // 현재 시간
        int index = 0;
        while (excuteCnt < jobs.length) {
            while (index < jobs.length && jobs[index][0] <= sec) {
                pq.offer(jobs[index]);
                index++;
            }

            if (pq.isEmpty()) {
                sec = jobs[index][0];
                continue;
            }

            int[] poll = pq.poll();
            answer += sec + poll[1] - poll[0];
            sec += poll[1];
            excuteCnt++;
        }

        return answer / jobs.length;
    }
}
