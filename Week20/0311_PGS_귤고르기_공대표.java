import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < tangerine.length; i++) {
            map.put(tangerine[i], map.getOrDefault(tangerine[i], 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(map.entrySet());
        entryList.sort(Map.Entry.comparingByValue((o1, o2) -> o2 - o1));

        for (Map.Entry<Integer, Integer> entry : entryList) {
            if (entry.getValue() >= k) {
                answer++;
                break;
            }

            k -= entry.getValue();
            answer++;
        }


        return answer;
    }
}
