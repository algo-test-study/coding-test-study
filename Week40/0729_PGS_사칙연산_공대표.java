class Solution {
    public int solution(String[] arr) {
        int n = (arr.length + 1) / 2;

        int[] nums = new int[n];
        char[] op = new char[n - 1];

        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                nums[i / 2] = Integer.parseInt(arr[i]);
            } else {
                op[i / 2] = arr[i].charAt(0);
            }
        }

        int[][] max = new int[n][n];
        int[][] min = new int[n][n];

        for (int i = 0; i < n; i++) {
            max[i][i] = nums[i];
            min[i][i] = nums[i];
        }

        for (int len = 2; len <= n; len++) {
            for (int left = 0; left + len - 1 < n; left++) {
                int right = left + len - 1;

                max[left][right] = Integer.MIN_VALUE;
                min[left][right] = Integer.MAX_VALUE;

                for (int k = left; k < right; k++) {
                    if (op[k] == '+') {
                        max[left][right] = Math.max(max[left][right], max[left][k] + max[k + 1][right]);
                        min[left][right] = Math.min(min[left][right], min[left][k] + min[k + 1][right]);
                    } else {
                        max[left][right] = Math.max(max[left][right], max[left][k] - min[k + 1][right]);
                        min[left][right] = Math.min(min[left][right], min[left][k] - max[k + 1][right]);
                    }
                }
            }
        }

        return max[0][n - 1];
    }
}
