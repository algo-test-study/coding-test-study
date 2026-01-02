class Solution {
    public int[] solution(int[] sequence, int k) {
        int n = sequence.length;

        int left = 0;
        long sum = 0;

        int bestL = 0;
        int bestR = n - 1;
        int bestLen = n;

        for (int right = 0; right < n; right++) {
            sum += sequence[right];

            while (sum > k) {
                sum -= sequence[left];
                left++;
            }

            if (sum == k) {
                int len = right - left + 1;

                if (len < bestLen) {
                    bestLen = len;
                    bestL = left;
                    bestR = right;
                }
            }
        }

        return new int[]{bestL, bestR};
    }
}
