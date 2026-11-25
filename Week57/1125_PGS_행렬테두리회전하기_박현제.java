import java.util.*;
class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] ans = new int[queries.length];
        int[][] arr = new int[rows + 1][columns + 1];
        int num = 1;

        for (int a = 1; a <= rows; a++) {
            for (int b = 1; b <= columns; b++) {
                arr[a][b] = num++;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int a1 = queries[i][0];
            int b1 = queries[i][1];
            int a2 = queries[i][2];
            int b2 = queries[i][3];

            int temp = arr[a1][b1];
            int min = temp;

            for (int a = a1; a < a2; a++) {
                arr[a][b1] = arr[a + 1][b1];
                min = Math.min(min, arr[a][b1]);
            }

            for (int b = b1; b < b2; b++) {
                arr[a2][b] = arr[a2][b + 1];
                min = Math.min(min, arr[a2][b]);
            }

            for (int a = a2; a > a1; a--) {
                arr[a][b2] = arr[a - 1][b2];
                min = Math.min(min, arr[a][b2]);
            }

            for (int b = b2; b > b1 + 1; b--) {
                arr[a1][b] = arr[a1][b - 1];
                min = Math.min(min, arr[a1][b]);
            }
            arr[a1][b1 + 1] = temp;
            ans[i] = min;
        }

        return ans;
    }
}
