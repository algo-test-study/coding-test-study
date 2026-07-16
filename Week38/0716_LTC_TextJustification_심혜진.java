import java.util.*;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            int j = i;
            int length = 0;

            while (j < words.length && length + words[j].length() + (j - i) <= maxWidth) {
                length += words[j].length();
                j++;
            }

            StringBuilder sb = new StringBuilder();
            int wordCount = j - i;

            if (j == words.length || wordCount == 1) {
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k != j - 1) {
                        sb.append(' ');
                    }
                }
                while (sb.length() < maxWidth) {
                    sb.append(' ');
                }
            } else {
                int spaces = maxWidth - length;
                int gap = wordCount - 1;
                int even = spaces / gap;
                int extra = spaces % gap;

                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k != j - 1) {
                        for (int s = 0; s < even; s++) {
                            sb.append(' ');
                        }
                        if (extra > 0) {
                            sb.append(' ');
                            extra--;
                        }
                    }
                }
            }

            result.add(sb.toString());
            i = j;
        }

        return result;
    }
}