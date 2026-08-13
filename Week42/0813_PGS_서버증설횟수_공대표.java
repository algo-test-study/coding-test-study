class Solution {
    public int solution(int[] players, int m, int k) {
        int[] servers = new int[24];
        int answer = 0;

        for (int i = 0; i < 24; i++) {
            int time = players[i] / m;
            int current = 0;

            for (int j = 0; j < i; j++) {
                if (i < j + k) {
                    current += servers[j];
                }
            }

            if (time > current) {
                int add = time - current;
                servers[i] = add;
                answer += add;
            }
        }

        return answer;
    }
}
