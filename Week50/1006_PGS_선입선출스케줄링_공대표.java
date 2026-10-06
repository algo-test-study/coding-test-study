class Solution {
    public int solution(int n, int[] cores) {
        if (n <= cores.length) {
            return n;
        }

        long left = 0;
        long right = 500000000;
        long time = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = cores.length;

            for (int core : cores) {
                count += mid / core;
            }

            if (count >= n) {
                time = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        long count = cores.length;

        for (int core : cores) {
            count += (time - 1) / core;
        }

        for (int i = 0; i < cores.length; i++) {
            if (time % cores[i] == 0) {
                count++;

                if (count == n) {
                    return i + 1;
                }
            }
        }

        return 0;
    }
}
