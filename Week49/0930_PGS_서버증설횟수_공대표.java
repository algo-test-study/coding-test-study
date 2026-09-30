class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] servers = new int[24];

        for (int i = 0; i < 24; i++) {
            int required = players[i] / m;

            int current = 0;

            for (int j = 0; j < i; j++) {
                if (j + k > i) {
                    current += servers[j];
                }
            }

            if (current < required) {
                int add = required - current;
                servers[i] = add;
                answer += add;
            }
        }

        return answer;
    }
}
