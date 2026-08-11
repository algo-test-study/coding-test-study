class Solution {

    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 1;

        for (int diff : diffs) {
            right = Math.max(right, diff);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canSolve(diffs, times, limit, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canSolve(
            int[] diffs,
            int[] times,
            long limit,
            int level
    ) {
        long totalTime = times[0];

        for (int i = 1; i < diffs.length; i++) {
            int diff = diffs[i];
            long currentTime = times[i];

            if (diff <= level) {
                totalTime += currentTime;
            } else {
                long wrongCount = diff - level;
                long previousTime = times[i - 1];

                totalTime += wrongCount * (currentTime + previousTime)
                        + currentTime;
            }

            if (totalTime > limit) {
                return false;
            }
        }

        return true;
    }
}