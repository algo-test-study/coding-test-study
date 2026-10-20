class Solution {
    int[] answer = new int[2];

    public int[] solution(int[][] arr) {
        tree(arr, 0, 0, arr.length);
        return answer;
    }

    public void tree(int[][] arr, int row, int col, int size) {
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

        tree(arr, row, col, half);
        tree(arr, row, col + half, half);
        tree(arr, row + half, col, half);
        tree(arr, row + half, col + half, half);
    }
}
