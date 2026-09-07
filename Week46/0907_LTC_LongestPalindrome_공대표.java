class Solution {
    public int longestPalindrome(String s) {
        int[] count = new int[128];

        for (char c : s.toCharArray()) {
            count[c]++;
        }

        int answer = 0;
        boolean odd = false;

        for (int value : count) {
            answer += (value / 2) * 2;

            if (value % 2 == 1) {
                odd = true;
            }
        }

        if (odd) {
            answer++;
        }

        return answer;
    }
}
