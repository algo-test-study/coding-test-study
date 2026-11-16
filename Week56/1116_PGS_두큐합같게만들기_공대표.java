
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length;
        long[] queue = new long[n * 2];

        long sum1 = 0;
        long sum2 = 0;

        for (int i = 0; i < n; i++) {
            queue[i] = queue1[i];
            queue[n + i] = queue2[i];

            sum1 += queue1[i];
            sum2 += queue2[i];
        }

        long total = sum1 + sum2;

        if (total % 2 != 0) {
            return -1;
        }

        long target = total / 2;
        long currentSum = sum1;

        int left = 0;
        int right = n;
        int answer = 0;

        while (left < 2 * n && right < 3 * n) {
            if (currentSum == target) {
                return answer;
            }

            if (currentSum > target) {
                currentSum -= queue[left % (2 * n)];
                left++;
            } else {
                currentSum += queue[right % (2 * n)];
                right++;
            }

            answer++;
        }

        return -1;
    }
}
