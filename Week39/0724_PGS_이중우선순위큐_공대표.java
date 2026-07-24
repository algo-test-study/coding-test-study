import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> reversePQ = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (String operation : operations) {
            String[] splitStr = operation.split(" ");

            if (splitStr[0].equals("D")) {
                if (splitStr[1].equals("1") && !reversePQ.isEmpty()) {
                    Integer poll = reversePQ.poll();
                    pq.remove(poll);
                } else if (splitStr[1].equals("-1") && !pq.isEmpty()) {
                    Integer poll = pq.poll();
                    reversePQ.remove(poll);
                }
            } else {
                int splitInt = Integer.parseInt(splitStr[1]);
                pq.add(splitInt);
                reversePQ.add(splitInt);
            }
        }

        int min = 0, max = 0;
        if (!pq.isEmpty()) {
            Integer poll = pq.poll();
            min = poll;
        }

        if (!reversePQ.isEmpty()) {
            Integer poll = reversePQ.poll();
            max = poll;
        }

        answer = new int[]{max, min};

        return answer;
    }
}
