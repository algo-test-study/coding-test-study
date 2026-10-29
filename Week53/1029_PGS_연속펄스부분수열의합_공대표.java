class Solution {
    public long solution(int[] sequence) {
        long answer = Long.MIN_VALUE;
        long positive = 0;
        long negative = 0;

        for (int i = 0; i < sequence.length; i++) {
            int number = sequence[i];

            if (i % 2 == 0) {
                positive = Math.max(0, positive) + number;
                negative = Math.max(0, negative) - number;
            } else {
                positive = Math.max(0, positive) - number;
                negative = Math.max(0, negative) + number;
            }

            answer = Math.max(answer, Math.max(positive, negative));
        }

        return answer;
    }
}
