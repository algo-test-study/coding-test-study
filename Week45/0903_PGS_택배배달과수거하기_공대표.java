class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        long delivery = 0;
        long pickup = 0;

        for (int i = n - 1; i >= 0; i--) {
            delivery += deliveries[i];
            pickup += pickups[i];

            while (delivery > 0 || pickup > 0) {
                answer += (i + 1L) * 2;
                delivery -= cap;
                pickup -= cap;
            }
        }

        return answer;
    }
}
