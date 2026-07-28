class Solution {
    public int solution(int[] money) {
        int n = money.length;

        if (n == 3) {
            return Math.max(money[0] + money[2],
                    Math.max(money[0], Math.max(money[1], money[2])));
        }

        int case1 = robLinear(money, 0, n - 2);
        int case2 = robLinear(money, 1, n - 1);

        return Math.max(case1, case2);
    }

    private int robLinear(int[] money, int start, int end) {
        if (start == end) return money[start];

        int prev2 = money[start];
        int prev1 = Math.max(money[start], money[start + 1]);

        for (int i = start + 2; i <= end; i++) {
            int cur = Math.max(prev1, prev2 + money[i]);
            prev2 = prev1;
            prev1 = cur;
        }

        return prev1;
    }
}
