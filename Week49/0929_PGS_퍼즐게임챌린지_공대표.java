class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 100000;

        while (left < right) {
            int level = (left + right) / 2;
            long totalTime = 0;

            for (int i = 0; i < diffs.length; i++) {
                if (diffs[i] <= level) {
                    totalTime += times[i];
                } else {
                    int failCount = diffs[i] - level;
                    long timePrev = i == 0 ? 0 : times[i - 1];

                    totalTime += (long) failCount * (times[i] + timePrev) + times[i];
                }

                if (totalTime > limit) {
                    break;
                }
            }

            if (totalTime <= limit) {
                right = level;
            } else {
                left = level + 1;
            }
        }

        return left;
    }
}
