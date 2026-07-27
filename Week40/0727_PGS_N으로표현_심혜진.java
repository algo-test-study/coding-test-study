import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if (N == number)
            return 1;

        List<Set<Integer>> dp = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }

        int repeated = 0;
        for (int i = 1; i <= 8; i++) {
            repeated = repeated * 10 + N;
            Set<Integer> current = dp.get(i);
            current.add(repeated);

            for (int j = 1; j < i; j++) {
                Set<Integer> aSet = dp.get(j);
                Set<Integer> bSet = dp.get(i - j);

                for (int a : aSet) {
                    for (int b : bSet) {
                        current.add(a + b);
                        current.add(a - b);
                        current.add(a * b);
                        if (b != 0)
                            current.add(a / b);
                    }
                }
            }

            if (current.contains(number))
                return i;
        }

        return -1;
    }
}
