import java.util.*;

class Solution {
    static Map<String, Integer> map;

    public String[] solution(String[] orders, int[] course) {
        List<String> answerList = new ArrayList<>();

        for (int size : course) {
            map = new HashMap<>();

            for (String order : orders) {
                char[] arr = order.toCharArray();
                Arrays.sort(arr);
                dfs(arr, "", 0, size);
            }

            int max = 0;
            for (int cnt : map.values()) {
                if (cnt >= 2) {
                    max = Math.max(max, cnt);
                }
            }

            for (String key : map.keySet()) {
                if (map.get(key) == max && max >= 2) {
                    answerList.add(key);
                }
            }
        }

        Collections.sort(answerList);

        return answerList.toArray(new String[0]);
    }

    public void dfs(char[] arr, String cur, int idx, int target) {
        if (cur.length() == target) {
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            dfs(arr, cur + arr[i], i + 1, target);
        }
    }
}
