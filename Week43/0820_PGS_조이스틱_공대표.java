class Solution {
    public int solution(String name) {
        int answer = 0;
        int length = name.length();

        for (int i = 0; i < length; i++) {
            char c = name.charAt(i);

            answer += Math.min(c - 'A', 'Z' - c + 1);
        }

        int move = length - 1;

        for (int i = 0; i < length; i++) {
            int next = i + 1;

            while (next < length && name.charAt(next) == 'A') {
                next++;
            }

            int rightAndBack = i * 2 + (length - next);
            int leftAndBack = i + (length - next) * 2;

            move = Math.min(move, rightAndBack);
            move = Math.min(move, leftAndBack);
        }

        return answer + move;
    }
}
