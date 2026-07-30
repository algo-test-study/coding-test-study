import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        List<String> words = new ArrayList<>();
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();

        int idx = 0;

        while (idx < message.length()) {
            int s = idx;

            while (idx < message.length() && message.charAt(idx) != ' ') {
                idx++;
            }

            words.add(message.substring(s, idx));
            start.add(s);
            end.add(idx - 1);

            idx++;
        }

        int wordCount = words.size();

        boolean[] spoiler = new boolean[wordCount];
        int[] reveal = new int[wordCount];
        Arrays.fill(reveal, -1);

        int range = 0;

        for (int i = 0; i < wordCount; i++) {
            while (range < spoiler_ranges.length && spoiler_ranges[range][1] < start.get(i)) {
                range++;
            }

            int temp = range;

            while (temp < spoiler_ranges.length && spoiler_ranges[temp][0] <= end.get(i)) {
                spoiler[i] = true;
                reveal[i] = temp;
                temp++;
            }
        }

        HashSet<String> normalWords = new HashSet<>();

        for (int i = 0; i < wordCount; i++) {
            if (!spoiler[i]) {
                normalWords.add(words.get(i));
            }
        }

        List<List<Integer>> revealList = new ArrayList<>();
        for (int i = 0; i < spoiler_ranges.length; i++) {
            revealList.add(new ArrayList<>());
        }

        for (int i = 0; i < wordCount; i++) {
            if (spoiler[i]) {
                revealList.get(reveal[i]).add(i);
            }
        }

        HashSet<String> appeared = new HashSet<>();
        int answer = 0;

        for (int i = 0; i < spoiler_ranges.length; i++) {
            for (int wordIdx : revealList.get(i)) {
                String word = words.get(wordIdx);

                if (normalWords.contains(word)) {
                    continue;
                }

                if (appeared.contains(word)) {
                    continue;
                }

                answer++;
                appeared.add(word);
            }
        }

        return answer;
    }
}
