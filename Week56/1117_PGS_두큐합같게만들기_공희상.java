class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length;
        long[] queue = new long[n * 2];
        long sum1 = 0;
        long total = 0;

        for (int i = 0; i < n; i++) {
            queue[i] = queue1[i];
            queue[n + i] = queue2[i];
            sum1 += queue1[i];
            total += queue1[i] + (long) queue2[i];
        }

        if (total % 2 != 0) {
            return -1;
        }

        long target = total / 2;
        long current = sum1;
        int left = 0;
        int right = n;
        int moves = 0;

        while (left < 2 * n && right < 3 * n) {
            if (current == target) {
                return moves;
            }

            if (current > target) {
                current -= queue[left++ % (2 * n)];
            } else {
                current += queue[right++ % (2 * n)];
            }

            moves++;
        }

        return -1;
    }
}
