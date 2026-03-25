import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        Map<Integer, Integer> map = new HashMap<>();

        int day = 0 ;
        for (int i = 0; i < progresses.length; i++) {
            while (progresses[i] + speeds[i] * day < 100) {
                day++;
            }

            map.put(day, map.getOrDefault(day, 0) + 1);
        }

        List<Integer> keys = new LinkedList<>(map.keySet());
        Collections.sort(keys);

        answer = new int[keys.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = map.get(keys.get(i));
        }

        return answer;
    }
}
