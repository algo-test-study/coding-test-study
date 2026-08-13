class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] expire = new int[players.length + k + 1];
        int active = 0;

        for (int i = 0; i < players.length; i++) {
            active -= expire[i];

            int need = players[i] / m;

            if (active < need) {
                int add = need - active;
                answer += add;
                active += add;
                expire[i + k] += add;
            }
        }

        return answer;
    }
}
