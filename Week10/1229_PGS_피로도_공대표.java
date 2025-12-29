class Solution {
    private boolean[] visited;
    private int result = 0;

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(k, dungeons, 0);
        return result;
    }

    private void dfs(int fatigue, int[][] dungeons, int count) {
        result = Math.max(result, count);

        for (int i = 0; i < dungeons.length; i++) {
            int need = dungeons[i][0]; // 최소 피로도
            int cost = dungeons[i][1]; // 소모 피로도

            if (!visited[i] && fatigue >= need) {
                visited[i] = true;
                dfs(fatigue - cost, dungeons, count + 1);
                visited[i] = false;
            }
        }
    }
}
