import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>(Arrays.asList(gems));
        int total = set.size();
        Map<String, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;

        int minLen = Integer.MAX_VALUE;
        int[] answer = new int[2];

        while (true) {
            if (map.size() == total) {
                if (right - left < minLen) {
                    minLen = right - left;
                    answer[0] = left + 1;
                    answer[1] = right;
                }

                String leftGem = gems[left];
                map.put(leftGem, map.get(leftGem) - 1);    
                
                if (map.get(leftGem) == 0) {
                    map.remove(leftGem);
                }

                left++;
            } else {
                if (right == gems.length) break;

                String rightGem = gems[right];
                map.put(rightGem, map.getOrDefault(rightGem, 0) + 1);
                
                right++;
            }
        }

        return answer;
    }
}
