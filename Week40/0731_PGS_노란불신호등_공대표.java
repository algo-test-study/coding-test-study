class Solution {

    public int solution(int[][] signals) {
        int repeatTime = 1;

        for (int i = 0; i < signals.length; i++) {
            int cycle = signals[i][0] + signals[i][1] + signals[i][2];
            repeatTime = getRepeatTime(repeatTime, cycle);
        }

        for (int currentTime = 1; currentTime <= repeatTime; currentTime++) {
            boolean allYellow = true;

            for (int i = 0; i < signals.length; i++) {
                int greenTime = signals[i][0];
                int yellowTime = signals[i][1];
                int redTime = signals[i][2];

                int cycle = greenTime + yellowTime + redTime;

                int position = (currentTime - 1) % cycle;

                if (position < greenTime || position >= greenTime + yellowTime) {
                    allYellow = false;
                    break;
                }
            }

            if (allYellow) {
                return currentTime;
            }
        }

        return -1;
    }

    private int getCommonDivisor(int a, int b) {
        while (b != 0) {

            int remain = a % b;
            a = b;
            b = remain;
        }

        return a;
    }

    private int getRepeatTime(int a, int b) {
        return a / getCommonDivisor(a, b) * b;
    }
}
