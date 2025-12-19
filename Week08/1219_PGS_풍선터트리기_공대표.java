class Solution {
 public int solution(int[] a) {
        int answer = 0;
     
        int n = a.length;
        if (n <= 2) return n;

        int[] leftMin = new int[n];
        int[] rightMin = new int[n];

        // leftMin
        int minVal = a[0];
        leftMin[0] = minVal;
        for (int i = 1; i < n; i++) {
            if (a[i] < minVal) minVal = a[i];
            leftMin[i] = minVal;
        }

        // rightMin
        minVal = a[n - 1];
        rightMin[n - 1] = minVal;
        for (int i = n - 2; i >= 0; i--) {
            if (a[i] < minVal) minVal = a[i];
            rightMin[i] = minVal;
        }

        for (int i = 0; i < n; i++) {
            if (a[i] <= leftMin[i] || a[i] <= rightMin[i]) {
                answer++;
            }
        }
     
        return answer;
    }
}
