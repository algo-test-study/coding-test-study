import java.util.*;

class Solution {
    class Word {
        String value;
        int count;
        
        public Word(String value, int count) {
            this.value = value;
            this.count = count;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        boolean exist = false;
        for (String word : words) {
            if (word.equals(target)) {
                exist = true;
                break;
            }
        }
        if (!exist) return 0;
        
        int answer = 0;
        
        Queue<Word> q = new LinkedList<>();
        boolean[] visited = new boolean[words.length + 1];
        q.offer(new Word(begin, 0));
        while (!q.isEmpty()) {
            Word current = q.poll();
            
            if (current.value.equals(target)) return current.count;
            
            for (int i = 0; i < words.length; i++) {
                if (canConvert(current.value, words[i]) && !visited[i]) {
                    q.offer(new Word(words[i], current.count+1));
                    visited[i] = true;
                    answer++;
                }
            }
        }
        return answer;
    }
    
    boolean canConvert(String s1, String s2) {
        int diff = 0;
        
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        return diff == 1;
    }
}
