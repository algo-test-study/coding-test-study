class Solution {

    public int rob(int[] nums) {
        int twoBefore = 0;
        int oneBefore = 0;

        for (int money : nums) {
            int current = Math.max(
                    oneBefore,        
                    twoBefore + money
            );

            twoBefore = oneBefore;
            oneBefore = current;
        }

        return oneBefore;
    }
}
