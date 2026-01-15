import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {

        // target이 words에 없으면 변환 불가능
        boolean exists = false;
        for (String word : words) {
            if (word.equals(target)) {
                exists = true;
                break;
            }
        }
        if (!exists) return 0;

        boolean[] visited = new boolean[words.length];
        Queue<Word> queue = new LinkedList<>();

        queue.offer(new Word(begin, 0));

        while (!queue.isEmpty()) {
            Word cur = queue.poll();

            if (cur.word.equals(target)) {
                return cur.step;
            }

            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && canConvert(cur.word, words[i])) {
                    visited[i] = true;
                    queue.offer(new Word(words[i], cur.step + 1));
                }
            }
        }

        return 0;
    }

    private boolean canConvert(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
        }
        return diff == 1;
    }
  
    static class Word {
        String word;
        int step;

        Word(String word, int step) {
            this.word = word;
            this.step = step;
        }
    }
}
