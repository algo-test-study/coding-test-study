class Solution {
    int answer = Integer.MAX_VALUE;

    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];

        dfs(begin, target, words, visited, 0);

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    public void dfs(String current, String target, String[] words, boolean[] visited, int count) {
        if (current.equals(target)) {
            answer = Math.min(answer, count);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (visited[i]) continue;

            int diff = 0;

            for (int j = 0; j < current.length(); j++) {
                if (current.charAt(j) != words[i].charAt(j)) {
                    diff++;
                }
            }

            if (diff == 1) {
                visited[i] = true;

                dfs(words[i], target, words, visited, count + 1);

                visited[i] = false;
            }
        }
    }
}
