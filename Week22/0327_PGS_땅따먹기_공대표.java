class Solution {
    public int solution(int[][] land) {
        int n = land.length;

        for (int i = 1; i < n; i++) {
            land[i][0] += Math.max(Math.max(land[i-1][1], land[i-1][2]), land[i-1][3]);
            land[i][1] += Math.max(Math.max(land[i-1][0], land[i-1][2]), land[i-1][3]);
            land[i][2] += Math.max(Math.max(land[i-1][0], land[i-1][1]), land[i-1][3]);
            land[i][3] += Math.max(Math.max(land[i-1][0], land[i-1][1]), land[i-1][2]);
        }

        int last = n - 1;
        
        return Math.max(Math.max(land[last][0], land[last][1]), Math.max(land[last][2], land[last][3]));
    }
}
