import java.util.*;

class Solution {
    public int longestPalindrome(String s) {
        Set<Character> set = new HashSet<>();
        int answer = 0;

        for (char c : s.toCharArray()) {
            if (!set.add(c)) {
                set.remove(c);
                answer+=2;
            }
        }         

        return set.isEmpty() ? answer : answer + 1;
    }
}
