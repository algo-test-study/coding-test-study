
import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> result = new ArrayList<>();

        int deployDay = 0;
        int count = 0;

        for (int i = 0; i < progresses.length; i++) {
            int remaining = 100 - progresses[i];
            int days = (remaining + speeds[i] - 1) / speeds[i];

            if (days > deployDay) {
                if (count > 0) {
                    result.add(count);
                }

                deployDay = days;
                count = 1;
            } else {
                count++;
            }
        }

        result.add(count);

        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
