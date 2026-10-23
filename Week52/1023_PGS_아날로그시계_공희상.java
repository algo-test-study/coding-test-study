class Solution {

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        long start = h1 * 3600L + m1 * 60L + s1;
        long end = h2 * 3600L + m2 * 60L + s2;
        long minuteCount = count(start, end, 3600, 59);
        long hourCount = count(start, end, 43200, 719);
        long duplicate = count(start, end, 43200, 1);
        return (int) (minuteCount + hourCount - duplicate);
    }

    private long count(long start, long end, long a, long b) {
        long first = (start * b + a - 1) / a;
        long last = (end * b) / a;
        return Math.max(0, last - first + 1);
    }
}
