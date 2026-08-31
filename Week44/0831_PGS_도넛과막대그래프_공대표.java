class Solution {
    public int[] solution(int[][] edges) {
        int[] in = new int[1000001];
        int[] out = new int[1000001];

        for (int[] edge : edges) {
            out[edge[0]]++;
            in[edge[1]]++;
        }

        int start = 0;
        int stick = 0;
        int eight = 0;

        for (int i = 1; i <= 1000000; i++) {
            if (in[i] == 0 && out[i] >= 2) {
                start = i;
            }

            if (in[i] >= 1 && out[i] == 0) {
                stick++;
            }

            if (in[i] >= 2 && out[i] == 2) {
                eight++;
            }
        }

        int donut = out[start] - stick - eight;

        return new int[]{start, donut, stick, eight};
    }
}
