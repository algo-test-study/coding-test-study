class Solution {
    public int[] solution(int[] sequence, int k) {
        int left = 0;
        int sum = 0;

        int bestStart = 0;
        int bestEnd = sequence.length - 1;
        int bestLength = Integer.MAX_VALUE;

        for (int right = 0; right < sequence.length; right++) {
            sum += sequence[right];

            while (sum > k) {
                sum -= sequence[left];
                left++;
            }

            if (sum == k) {
                int length = right - left + 1;

                if (length < bestLength) {
                    bestLength = length;
                    bestStart = left;
                    bestEnd = right;
                }
            }
        }

        return new int[]{bestStart, bestEnd};
    }
}
