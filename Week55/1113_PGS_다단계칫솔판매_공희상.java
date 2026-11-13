import java.util.*;

class Solution {

    public int[] solution(
            String[] enroll,
            String[] referral,
            String[] seller,
            int[] amount
    ) {
        int n = enroll.length;

        Map<String, Integer> nameToIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {
            nameToIndex.put(enroll[i], i);
        }

        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            if (referral[i].equals("-")) {
                parent[i] = -1;
            } else {
                parent[i] = nameToIndex.get(referral[i]);
            }
        }

        int[] answer = new int[n];

        for (int i = 0; i < seller.length; i++) {

            int current = nameToIndex.get(seller[i]);
            int money = amount[i] * 100;

            while (current != -1 && money > 0) {

                int commission = money / 10;

                answer[current] += money - commission;

                money = commission;
                current = parent[current];
            }
        }

        return answer;
    }
}
