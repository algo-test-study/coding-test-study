class Solution {

    public long solution(int[] sequence) {
        long plus = sequence[0];
        long minus = -sequence[0];

        long answer = Math.max(plus, minus);

        for (int i = 1; i < sequence.length; i++) {

            long value = (i % 2 == 0)
                    ? sequence[i]
                    : -sequence[i];

            plus = Math.max(value, plus + value);
            minus = Math.max(-value, minus - value);

            answer = Math.max(answer, Math.max(plus, minus));
        }

        return answer;
    }
}
