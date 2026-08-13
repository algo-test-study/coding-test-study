class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] expire = new int[24 + k + 1];

        int running = 0;
        int n = players.length;

        for (int t = 0; t < n; t++) {
            running -= expire[t];

            int need = players[t] / m;
            if (players[t] % m != 0) {
                need++;
            }

            if (need > running) {
                int add = need - running;
                answer += add;
                running += add;
                int end = t + k;
                if (end < expire.length) {
                    expire[end] += add;
                }
            }
        }

        return answer;
    }
}
