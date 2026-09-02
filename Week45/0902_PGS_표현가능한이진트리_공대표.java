class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);

            int length = 1;

            while (length < binary.length()) {
                length = length * 2 + 1;
            }

            StringBuilder sb = new StringBuilder();

            for (int j = binary.length(); j < length; j++) {
                sb.append('0');
            }

            sb.append(binary);

            answer[i] = isValid(sb.toString(), 0, sb.length() - 1) ? 1 : 0;
        }

        return answer;
    }

    private boolean isValid(String binary, int left, int right) {
        if (left > right) {
            return true;
        }

        int mid = (left + right) / 2;

        if (binary.charAt(mid) == '0') {
            for (int i = left; i <= right; i++) {
                if (binary.charAt(i) == '1') {
                    return false;
                }
            }

            return true;
        }

        return isValid(binary, left, mid - 1) && isValid(binary, mid + 1, right);
    }
}
