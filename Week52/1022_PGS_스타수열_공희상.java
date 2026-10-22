import java.util.Arrays;

class Solution {
    public int solution(int[] a) {
        int n = a.length;
        int[] count = new int[n];
        int[] lastEnd = new int[n];
        Arrays.fill(lastEnd, -1);

        int maxPairs = 0;

        for (int i = 0; i < n - 1; i++) {
            int left = a[i];
            int right = a[i + 1];

            if (left == right) {
                continue;
            }

            if (lastEnd[left] < i) {
                count[left]++;
                lastEnd[left] = i + 1;
                maxPairs = Math.max(maxPairs, count[left]);
            }

            if (lastEnd[right] < i) {
                count[right]++;
                lastEnd[right] = i + 1;
                maxPairs = Math.max(maxPairs, count[right]);
            }
        }

        return maxPairs * 2;
    }
}
