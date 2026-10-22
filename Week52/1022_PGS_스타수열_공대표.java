class Solution {
    public int solution(int[] a) {
        int[] answer = new int[a.length];
        int max = 0;

        for (int num : a) {
            answer[num]++;
        }

        for (int i = 0; i < a.length; i++) {
            if (answer[i] == 0 || answer[i] * 2 <= max) {
                continue;
            }

            int count = 0;
            for (int j = 0; j < a.length - 1; j++) {
                if (a[j] != i && a[j + 1] != i) {
                    continue;
                }

                if (a[j] == a[j + 1]) {
                    continue;
                }

                count++;
                j++;
            }

            max = Math.max(max, count * 2);
        }

        return max;
    }
}
