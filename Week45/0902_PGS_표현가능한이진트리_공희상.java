class Solution {

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);

            int treeSize = 1;

            // 포화 이진트리 노드 수: 1, 3, 7, 15, 31, 63 ...
            while (treeSize < binary.length()) {
                treeSize = treeSize * 2 + 1;
            }

            // 앞에 0을 채워 포화 이진트리 형태로 맞춤
            String padded = "0".repeat(treeSize - binary.length()) + binary;

            answer[i] = isValid(padded, 0, padded.length() - 1) ? 1 : 0;
        }

        return answer;
    }

    private boolean isValid(String binary, int left, int right) {
        if (left >= right) {
            return true;
        }

        int mid = (left + right) / 2;

        int leftMid = (left + mid - 1) / 2;
        int rightMid = (mid + 1 + right) / 2;

        // 부모가 0인데 자식이 1이면 불가능
        if (binary.charAt(mid) == '0') {
            if (binary.charAt(leftMid) == '1'
                    || binary.charAt(rightMid) == '1') {
                return false;
            }
        }

        return isValid(binary, left, mid - 1)
                && isValid(binary, mid + 1, right);
    }
}
