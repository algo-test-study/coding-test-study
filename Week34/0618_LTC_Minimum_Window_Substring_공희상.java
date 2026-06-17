import java.util.HashMap;
import java.util.Map;

class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> requiredCharacterCounts = new HashMap<>();

        for (char character : t.toCharArray()) {
            requiredCharacterCounts.put(
                    character,
                    requiredCharacterCounts.getOrDefault(character, 0) + 1
            );
        }

        Map<Character, Integer> windowCharacterCounts = new HashMap<>();

        int requiredKinds = requiredCharacterCounts.size();
        int matchedKinds = 0;

        int leftIndex = 0;
        int minimumLength = Integer.MAX_VALUE;
        int minimumStartIndex = 0;

        for (int rightIndex = 0; rightIndex < s.length(); rightIndex++) {
            char rightCharacter = s.charAt(rightIndex);

            windowCharacterCounts.put(
                    rightCharacter,
                    windowCharacterCounts.getOrDefault(rightCharacter, 0) + 1
            );

            if (requiredCharacterCounts.containsKey(rightCharacter)
                    && windowCharacterCounts.get(rightCharacter)
                    .intValue() == requiredCharacterCounts.get(rightCharacter).intValue()) {
                matchedKinds++;
            }

            while (matchedKinds == requiredKinds) {
                int currentLength = rightIndex - leftIndex + 1;

                if (currentLength < minimumLength) {
                    minimumLength = currentLength;
                    minimumStartIndex = leftIndex;
                }

                char leftCharacter = s.charAt(leftIndex);

                windowCharacterCounts.put(
                        leftCharacter,
                        windowCharacterCounts.get(leftCharacter) - 1
                );

                if (requiredCharacterCounts.containsKey(leftCharacter)
                        && windowCharacterCounts.get(leftCharacter)
                        < requiredCharacterCounts.get(leftCharacter)) {
                    matchedKinds--;
                }

                leftIndex++;
            }
        }

        if (minimumLength == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(minimumStartIndex, minimumStartIndex + minimumLength);
    }
}
