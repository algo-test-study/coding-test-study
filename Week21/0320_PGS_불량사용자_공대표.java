import java.util.*;

class Solution {
    static Set<String> set = new HashSet<>();
    static boolean[] visited;

    public int solution(String[] user_id, String[] banned_id) {
        visited = new boolean[user_id.length];

        dfs(user_id, banned_id, 0, new ArrayList<>());

        return set.size();
    }

    static void dfs(String[] user_id, String[] banned_id, int depth, List<String> list) {
        if (depth == banned_id.length) {
            List<String> temp = new ArrayList<>(list);
            Collections.sort(temp);

            String key = String.join(",", temp);
            set.add(key);

            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if (visited[i]) continue;

            String user = user_id[i];
            String banned = banned_id[depth];

            if (user.length() != banned.length()) continue;

            boolean flag = true;

            for (int j = 0; j < user.length(); j++) {
                if (banned.charAt(j) == '*') continue;
                
                if (user.charAt(j) != banned.charAt(j)) {
                    flag = false;
                    break;
                }
            }

            if (!flag) continue;

            visited[i] = true;
            list.add(user);

            dfs(user_id, banned_id, depth + 1, list);

            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}
