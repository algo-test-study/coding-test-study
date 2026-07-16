import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();

        int index = 0;

        while (index < words.length) {
            int start = index;
            int wordsLength = 0;

            while (index < words.length
                    && wordsLength + words[index].length() + (index - start) <= maxWidth) {
                wordsLength += words[index].length();
                index++;
            }

            int end = index - 1;
            int wordCount = end - start + 1;

            boolean isLastLine = index == words.length;

            String line;

            if (isLastLine || wordCount == 1) {
                line = buildLeftJustifiedLine(words, start, end, maxWidth);
            } else {
                line = buildFullyJustifiedLine(words, start, end, wordsLength, maxWidth);
            }

            result.add(line);
        }

        return result;
    }

    private String buildLeftJustifiedLine(
            String[] words,
            int start,
            int end,
            int maxWidth
    ) {
        StringBuilder line = new StringBuilder();

        for (int i = start; i <= end; i++) {
            line.append(words[i]);

            if (i < end) {
                line.append(' ');
            }
        }

        appendSpaces(line, maxWidth - line.length());

        return line.toString();
    }

    private String buildFullyJustifiedLine(
            String[] words,
            int start,
            int end,
            int wordsLength,
            int maxWidth
    ) {
        StringBuilder line = new StringBuilder();

        int wordCount = end - start + 1;
        int gaps = wordCount - 1;

        int totalSpaces = maxWidth - wordsLength;
        int baseSpace = totalSpaces / gaps;
        int extraSpace = totalSpaces % gaps;

        for (int i = start; i <= end; i++) {
            line.append(words[i]);

            if (i == end) {
                break;
            }

            int spaces = baseSpace;

            if (i - start < extraSpace) {
                spaces++;
            }

            appendSpaces(line, spaces);
        }

        return line.toString();
    }

    private void appendSpaces(StringBuilder line, int count) {
        for (int i = 0; i < count; i++) {
            line.append(' ');
        }
    }
}
