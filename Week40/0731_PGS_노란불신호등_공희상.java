class Solution {

    public int solution(int[][] signals) {
        long repeatPeriod = 1;

        for (int[] signal : signals) {
            int cycle = signal[0] + signal[1] + signal[2];
            repeatPeriod = lcm(repeatPeriod, cycle);
        }

        for (long time = 1; time <= repeatPeriod; time++) {
            if (isAllYellow(signals, time)) {
                return (int) time;
            }
        }

        return -1;
    }

    private boolean isAllYellow(int[][] signals, long time) {
        for (int[] signal : signals) {
            int green = signal[0];
            int yellow = signal[1];
            int red = signal[2];

            int cycle = green + yellow + red;

            long position = (time - 1) % cycle;
          
            boolean isYellow =
                    green <= position
                    && position < green + yellow;

            if (!isYellow) {
                return false;
            }
        }

        return true;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long remainder = a % b;
            a = b;
            b = remainder;
        }

        return a;
    }
}
