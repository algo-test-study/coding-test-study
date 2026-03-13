class Solution {
    public int solution(int[] money) {
        if (money.length == 1) return money[0];
       
        int n = money.length;

        int case1 = dp(money, 0, n - 2);
        int case2 = dp(money, 1, n - 1);

        return Math.max(case1, case2);
    }

    private int dp(int[] money, int start, int end) {
        int prev2 = 0;
        int prev1 = 0;

        for (int i = start; i <= end; i++) {
            int cur = Math.max(prev1, prev2 + money[i]);

            prev2 = prev1;
            prev1 = cur;
        }

        return prev1;
    }
}
