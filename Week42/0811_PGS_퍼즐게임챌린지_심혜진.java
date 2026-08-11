class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 100000;

        while (left < right) {
            int level = (left + right) / 2;

            if (canSolve(diffs, times, limit, level)) {
                right = level;
            } else {
                left = level + 1;
            }
        }

        return left;
    }

    private boolean canSolve(int[] diffs, int[] times, long limit, int level) {
        long total = times[0];

        for (int i = 1; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                total += times[i];
            } else {
                long fail = diffs[i] - level;
                total += fail * (times[i] + times[i - 1]) + times[i];
            }

            if (total > limit) {
                return false;
            }
        }

        return true;
    }
}