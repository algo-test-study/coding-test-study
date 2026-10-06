class Solution {
    public int solution(int n, int[] cores) {
        int coreCount = cores.length;
        
        if (n <= coreCount) {
            return n;
        }
        
        long left = 0;
        long right = n * 10_000L;
        
        while (left < right) {
            long mid = (left + right) / 2;
            
            long count = cores.length;

            for (int core : cores) {
                count += mid / core;
            }
            
            if (count >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        long time = left;
        
        long count = coreCount;

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

        return -1;
    }
}
