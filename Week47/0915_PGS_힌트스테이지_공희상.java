class Solution {

    private int[][] cost;
    private int[][] hint;
    private int[] hintCount;
    private int n;
    private int answer;

    public int solution(int[][] cost, int[][] hint) {
        this.cost = cost;
        this.hint = hint;
        this.n = cost.length;
        this.hintCount = new int[n];
        this.answer = Integer.MAX_VALUE;

        dfs(0, 0);

        return answer;
    }

    private void dfs(int stage, int total) {
        if (total >= answer) {
            return;
        }

        if (stage == n) {
            answer = Math.min(answer, total);
            return;
        }

        int used = Math.min(hintCount[stage], n - 1);
        int nextTotal = total + cost[stage][used];

        if (stage == n - 1) {
            answer = Math.min(answer, nextTotal);
            return;
        }

        dfs(stage + 1, nextTotal);

        for (int i = 1; i < hint[stage].length; i++) {
            int target = hint[stage][i] - 1;
            hintCount[target]++;
        }

        dfs(
            stage + 1,
            nextTotal + hint[stage][0]
        );

        for (int i = 1; i < hint[stage].length; i++) {
            int target = hint[stage][i] - 1;
            hintCount[target]--;
        }
    }
}
