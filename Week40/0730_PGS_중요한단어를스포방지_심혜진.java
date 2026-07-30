import java.util.*;

class Solution {
    static class Word {
        String text;
        int start, end;
        boolean hasSpoiler;
        boolean hasPlain;
    }

    public int solution(String message, int[][] spoiler_ranges) {
        int n = message.length();

        List<Word> words = new ArrayList<>();
        int[] idxToWord = new int[n];
        Arrays.fill(idxToWord, -1);

        int i = 0;
        while (i < n) {
            if (message.charAt(i) == ' ') {
                i++;
                continue;
            }
            int start = i;
            while (i < n && message.charAt(i) != ' ') i++;
            int end = i - 1;
            Word w = new Word();
            w.text = message.substring(start, i);
            w.start = start;
            w.end = end;
            int wi = words.size();
            words.add(w);
            for (int p = start; p <= end; p++) {
                idxToWord[p] = wi;
            }
        }

        boolean[] spoilerMask = new boolean[n];
        for (int[] r : spoiler_ranges) {
            int s = r[0], e = r[1];
            for (int p = s; p <= e; p++) {
                spoilerMask[p] = true;
            }
        }

        for (Word w : words) {
            w.hasSpoiler = false;
            w.hasPlain = false;
            for (int p = w.start; p <= w.end; p++) {
                if (spoilerMask[p]) w.hasSpoiler = true;
                else w.hasPlain = true;
            }
        }

        Set<String> plainExist = new HashSet<>();
        for (Word w : words) {
            if (w.hasPlain) plainExist.add(w.text);
        }

        Set<String> revealedImportant = new HashSet<>();
        int answer = 0;

        for (int[] r : spoiler_ranges) {
            int s = r[0], e = r[1];
            List<Integer> openedWords = new ArrayList<>();
            int lastWi = -1;
            int p = s;
            while (p <= e) {
                int wi = idxToWord[p];
                if (wi != -1 && wi != lastWi) {
                    openedWords.add(wi);
                    lastWi = wi;
                }
                p++;
            }

            for (int wi : openedWords) {
                Word w = words.get(wi);
                if (!w.hasSpoiler) continue;
                if (plainExist.contains(w.text)) continue;
                if (revealedImportant.contains(w.text)) continue;
                revealedImportant.add(w.text);
                answer++;
            }
        }

        return answer;
    }
}
