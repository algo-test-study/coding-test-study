class Solution {

    public int[] solution(int[][] arr) {
        int[] answer = new int[2];

        compress(arr, 0, 0, arr.length, answer);

        return answer;
    }

    private void compress(
            int[][] arr,
            int row,
            int col,
            int size,
            int[] answer
    ) {

        int value = arr[row][col];
        boolean same = true;

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (arr[i][j] != value) {
                    same = false;
                    break;
                }
            }

            if (!same) {
                break;
            }
        }

        if (same) {
            answer[value]++;
            return;
        }

        int half = size / 2;

        compress(arr, row, col, half, answer);
        compress(arr, row, col + half, half, answer);
        compress(arr, row + half, col, half, answer);
        compress(arr, row + half, col + half, half, answer);
    }
}
