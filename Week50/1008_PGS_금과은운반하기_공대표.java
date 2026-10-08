class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long left = 0;
        long right = 400000000000001L;
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;

            long gold = 0;
            long silver = 0;
            long total = 0;

            for (int i = 0; i < g.length; i++) {
                long roundTime = (long) t[i] * 2;
                long count = mid / roundTime;

                if (mid % roundTime >= t[i]) {
                    count++;
                }

                long capacity = count * w[i];

                gold += Math.min((long) g[i], capacity);
                silver += Math.min((long) s[i], capacity);
                total += Math.min((long) g[i] + s[i], capacity);
            }

            if (gold >= a && silver >= b && total >= (long) a + b) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }
}
