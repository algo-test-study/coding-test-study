
import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;

        Map<String, Integer> map = new HashMap<>();
        int[] parent = new int[n];
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            map.put(enroll[i], i);
        }

        for (int i = 0; i < n; i++) {
            if (referral[i].equals("-")) {
                parent[i] = -1;
            } else {
                parent[i] = map.get(referral[i]);
            }
        }

        for (int i = 0; i < seller.length; i++) {
            int current = map.get(seller[i]);
            int profit = amount[i] * 100;

            while (current != -1 && profit > 0) {
                int commission = profit / 10;

                answer[current] += profit - commission;

                profit = commission;
                current = parent[current];
            }
        }

        return answer;
    }
}
