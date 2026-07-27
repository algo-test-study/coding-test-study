import java.util.*;

class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> dp = new ArrayList<>();
        
        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }

        for (int i = 1; i <= 8; i++) {
            Set<Integer> cur = dp.get(i);

            int concat = 0;
            for (int k = 0; k < i; k++) {
                concat = concat * 10 + N;
            }
            cur.add(concat);

            for (int j = 1; j < i; j++) {
                for (int a : dp.get(j)) {
                    for (int b : dp.get(i - j)) {
                        cur.add(a + b);
                        cur.add(a - b);
                        cur.add(a * b);
                        if (b != 0) {
                            cur.add(a / b);
                        }
                    }
                }
            }

            if (cur.contains(number)) {
                return i;
            }
        }

        return -1;
    }
}
