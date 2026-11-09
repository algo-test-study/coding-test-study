class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        long start = h1 * 3600L + m1 * 60L + s1;
        long end = h2 * 3600L + m2 * 60L + s2;

        long minuteFirst = (59 * start + 3599) / 3600;
        long minuteLast = (59 * end) / 3600;
        long minuteCount = Math.max(0, minuteLast - minuteFirst + 1);

        long hourFirst = (719 * start + 43199) / 43200;
        long hourLast = (719 * end) / 43200;
        long hourCount = Math.max(0, hourLast - hourFirst + 1);

        long bothFirst = (start + 43199) / 43200;
        long bothLast = end / 43200;
        long bothCount = Math.max(0, bothLast - bothFirst + 1);

        int answer = (int) (minuteCount + hourCount - bothCount);

        return answer;
    }
}
