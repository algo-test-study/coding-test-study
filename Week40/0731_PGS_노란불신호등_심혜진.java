class Solution {
    public int solution(int[][] signals) {
        int n = signals.length;
        int[] period = new int[n];
        for (int i = 0; i < n; i++) {
            period[i] = signals[i][0] + signals[i][1] + signals[i][2];
        }

        long maxTime = 1;
        for (int i = 0; i < n; i++) {
            maxTime = lcm(maxTime, period[i]);
        }

        for (long t = 1; t <= maxTime; t++) {
            boolean allYellow = true;
            for (int i = 0; i < n; i++) {
                int G = signals[i][0];
                int Y = signals[i][1];
                int R = signals[i][2];
                int T = period[i];
                int pos = (int) ((t - 1) % T);
                if (!(pos >= G && pos < G + Y)) {
                    allYellow = false;
                    break;
                }
            }
            if (allYellow) return (int) t;
        }
        return -1;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
        }
}
