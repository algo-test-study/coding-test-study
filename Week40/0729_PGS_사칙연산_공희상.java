class Solution {

    public int solution(String[] arr) {
        int numberCount = arr.length / 2 + 1;

        int[] numbers = new int[numberCount];
        char[] operators = new char[numberCount - 1];

        // 숫자와 연산자를 분리한다.
        for (int i = 0; i < numberCount; i++) {
            numbers[i] = Integer.parseInt(arr[i * 2]);

            if (i < numberCount - 1) {
                operators[i] = arr[i * 2 + 1].charAt(0);
            }
        }

        int[][] maxDp = new int[numberCount][numberCount];
        int[][] minDp = new int[numberCount][numberCount];

        for (int i = 0; i < numberCount; i++) {
            for (int j = 0; j < numberCount; j++) {
                maxDp[i][j] = Integer.MIN_VALUE;
                minDp[i][j] = Integer.MAX_VALUE;
            }
        }

        // 숫자 하나만 있는 구간은 최댓값과 최솟값이 자기 자신이다.
        for (int i = 0; i < numberCount; i++) {
            maxDp[i][i] = numbers[i];
            minDp[i][i] = numbers[i];
        }

        // length는 구간에 포함되는 숫자의 개수다.
        for (int length = 2; length <= numberCount; length++) {

            for (int start = 0;
                 start + length <= numberCount;
                 start++) {

                int end = start + length - 1;

                for (int split = start; split < end; split++) {
                    char operator = operators[split];

                    if (operator == '+') {
                        int maxValue =
                                maxDp[start][split]
                                + maxDp[split + 1][end];

                        int minValue =
                                minDp[start][split]
                                + minDp[split + 1][end];

                        maxDp[start][end] =
                                Math.max(maxDp[start][end], maxValue);

                        minDp[start][end] =
                                Math.min(minDp[start][end], minValue);

                    } else {
                        int maxValue =
                                maxDp[start][split]
                                - minDp[split + 1][end];

                        int minValue =
                                minDp[start][split]
                                - maxDp[split + 1][end];

                        maxDp[start][end] =
                                Math.max(maxDp[start][end], maxValue);

                        minDp[start][end] =
                                Math.min(minDp[start][end], minValue);
                    }
                }
            }
        }

        return maxDp[0][numberCount - 1];
    }
}
