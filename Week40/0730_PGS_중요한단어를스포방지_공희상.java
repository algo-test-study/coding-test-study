import java.util.*;

class Solution {

    public int solution(String message, int[][] spoilerRanges) {
        int messageLength = message.length();
        int rangeCount = spoilerRanges.length;

        int[] spoilerIndex = new int[messageLength];
        Arrays.fill(spoilerIndex, -1);

        for (int rangeIndex = 0; rangeIndex < rangeCount; rangeIndex++) {
            int start = spoilerRanges[rangeIndex][0];
            int end = spoilerRanges[rangeIndex][1];

            for (int index = start; index <= end; index++) {
                spoilerIndex[index] = rangeIndex;
            }
        }

        List<List<String>> revealedWords = new ArrayList<>();

        for (int i = 0; i < rangeCount; i++) {
            revealedWords.add(new ArrayList<>());
        }

        Set<String> normalWords = new HashSet<>();

        int index = 0;

        while (index < messageLength) {
            if (message.charAt(index) == ' ') {
                index++;
                continue;
            }

            int wordStart = index;

            while (index < messageLength && message.charAt(index) != ' ') {
                index++;
            }

            int wordEnd = index - 1;
            String word = message.substring(wordStart, index);

            int lastSpoilerRange = -1;

            for (int position = wordStart; position <= wordEnd; position++) {
                lastSpoilerRange = Math.max(
                    lastSpoilerRange,
                    spoilerIndex[position]
                );
            }

            if (lastSpoilerRange == -1) {
                normalWords.add(word);
            } else {
                revealedWords.get(lastSpoilerRange).add(word);
            }
        }

        Set<String> seenSpoilerWords = new HashSet<>();

        int answer = 0;

        for (int rangeIndex = 0; rangeIndex < rangeCount; rangeIndex++) {
            for (String word : revealedWords.get(rangeIndex)) {

                boolean appearedInNormalArea = normalWords.contains(word);
                boolean alreadyRevealed = seenSpoilerWords.contains(word);

                if (!appearedInNormalArea && !alreadyRevealed) {
                    answer++;
                }

                seenSpoilerWords.add(word);
            }
        }

        return answer;
    }
}
