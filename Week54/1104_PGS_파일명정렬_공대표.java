
import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = files.clone();

        Arrays.sort(answer, (a, b) -> {
            String headA = "";
            String headB = "";
            int numberA = 0;
            int numberB = 0;

            int i = 0;
            while (i < a.length() && !Character.isDigit(a.charAt(i))) {
                headA += a.charAt(i);
                i++;
            }

            int j = i;
            while (j < a.length() && Character.isDigit(a.charAt(j)) && j - i < 5) {
                j++;
            }
            numberA = Integer.parseInt(a.substring(i, j));

            i = 0;
            while (i < b.length() && !Character.isDigit(b.charAt(i))) {
                headB += b.charAt(i);
                i++;
            }

            j = i;
            while (j < b.length() && Character.isDigit(b.charAt(j)) && j - i < 5) {
                j++;
            }
            numberB = Integer.parseInt(b.substring(i, j));

            int headCompare = headA.compareToIgnoreCase(headB);

            if (headCompare != 0) {
                return headCompare;
            }

            return Integer.compare(numberA, numberB);
        });

        return answer;
    }
}
