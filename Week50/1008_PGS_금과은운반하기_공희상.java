
class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long left = 0;
        long right = 400_000_000_000_000L;

        while (left < right) {
            long mid = left + (right - left) / 2;

            long gold = 0;
            long silver = 0;
            long total = 0;

            for (int i = 0; i < g.length; i++) {
                long count = mid / (2L * t[i]);

                if (mid % (2L * t[i]) >= t[i]) {
                    count++;
                }

                long capacity = count * w[i];

                gold += Math.min(capacity, (long) g[i]);
                silver += Math.min(capacity, (long) s[i]);
                total += Math.min(capacity, (long) g[i] + s[i]);
            }

            if (gold >= a && silver >= b && total >= (long) a + b) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
