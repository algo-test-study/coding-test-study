import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 0;

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < clothes.length; i++) {
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1);
        }

        answer = 1;
        for (Integer value : map.values()) {
            answer *= (value + 1);
        }
        answer -= 1;

        return answer;
    }
}
