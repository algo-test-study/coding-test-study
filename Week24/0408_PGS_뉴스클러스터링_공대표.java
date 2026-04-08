import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        for (int i = 0; i < str1.length() - 1; i++) {
            char a = str1.charAt(i);
            char b = str1.charAt(i + 1);
            if (Character.isLetter(a) && Character.isLetter(b)) {
                String s = "" + a + b;
                map1.put(s, map1.getOrDefault(s, 0) + 1);
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            char a = str2.charAt(i);
            char b = str2.charAt(i + 1);
            if (Character.isLetter(a) && Character.isLetter(b)) {
                String s = "" + a + b;
                map2.put(s, map2.getOrDefault(s, 0) + 1);
            }
        }

        Set<String> set = new HashSet<>();
        set.addAll(map1.keySet());
        set.addAll(map2.keySet());

        int intersection = 0;
        int union = 0;

        for (String key : set) {
            int v1 = map1.getOrDefault(key, 0);
            int v2 = map2.getOrDefault(key, 0);

            intersection += Math.min(v1, v2);
            union += Math.max(v1, v2);
        }

        if (union == 0) return 65536;

        return (int)((double)intersection / union * 65536);
    }
}
