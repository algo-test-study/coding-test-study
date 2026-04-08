import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        Map<String, Integer> map1 = makeMultiSet(str1);
        Map<String, Integer> map2 = makeMultiSet(str2);

        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        int intersection = 0;
        int union = 0;

        for (String key : allKeys) {
            int count1 = map1.getOrDefault(key, 0);
            int count2 = map2.getOrDefault(key, 0);

            intersection += Math.min(count1, count2);
            union += Math.max(count1, count2);
        }

        if (union == 0) {
            return 65536;
        }

        double jaccard = (double) intersection / union;
        return (int) (jaccard * 65536);
    }

    private Map<String, Integer> makeMultiSet(String str) {
        Map<String, Integer> map = new HashMap<>();
        str = str.toUpperCase();

        for (int i = 0; i < str.length() - 1; i++) {
            char first = str.charAt(i);
            char second = str.charAt(i + 1);

            if (Character.isLetter(first) && Character.isLetter(second)) {
                String element = "" + first + second;
                map.put(element, map.getOrDefault(element, 0) + 1);
            }
        }

        return map;
    }
}
