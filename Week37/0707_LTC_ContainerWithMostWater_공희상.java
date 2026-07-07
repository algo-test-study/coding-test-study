class Solution {
    public int maxArea(int[] height) {
        int max = 0;

        for (int left = 0; left < height.length; left++) {
            for (int right = left + 1; right < height.length; right ++) {
                int width = right - left;
                int contain = Math.min(height[left], height[right]);
                int area = width * contain;

                max = Math.max(max, area);
            }
        }

        return max;
    }
}
