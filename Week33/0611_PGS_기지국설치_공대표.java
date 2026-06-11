class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int prev = 0;

        for (int station : stations) {
            int start = station - w - 1;
            int gap = start - prev;
            if (gap > 0) {
                answer += Math.ceil((double) gap / (2 * w + 1));
            }
            prev = station + w;
        }

        if (prev < n) {
            int gap = n - prev;
            answer += Math.ceil((double) gap / (2 * w + 1));
        }

        return answer;
    }
}
