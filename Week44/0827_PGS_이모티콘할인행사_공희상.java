class Solution {

    private static final int[] DISCOUNTS = {10, 20, 30, 40};

    private int maxSubscriber = 0;
    private int maxSales = 0;

    public int[] solution(int[][] users, int[] emoticons) {
        int[] discountRates = new int[emoticons.length];

        dfs(0, discountRates, users, emoticons);

        return new int[]{maxSubscriber, maxSales};
    }

    private void dfs(
            int depth,
            int[] discountRates,
            int[][] users,
            int[] emoticons
    ) {
        if (depth == emoticons.length) {
            calculate(discountRates, users, emoticons);
            return;
        }

        for (int discount : DISCOUNTS) {
            discountRates[depth] = discount;
            dfs(depth + 1, discountRates, users, emoticons);
        }
    }

    private void calculate(
            int[] discountRates,
            int[][] users,
            int[] emoticons
    ) {
        int subscriber = 0;
        int sales = 0;

        for (int[] user : users) {
            int requiredDiscount = user[0];
            int subscriptionLimit = user[1];

            int totalPrice = 0;

            for (int i = 0; i < emoticons.length; i++) {
                if (discountRates[i] >= requiredDiscount) {
                    totalPrice += emoticons[i]
                            * (100 - discountRates[i])
                            / 100;
                }
            }

            if (totalPrice >= subscriptionLimit) {
                subscriber++;
            } else {
                sales += totalPrice;
            }
        }

        if (subscriber > maxSubscriber) {
            maxSubscriber = subscriber;
            maxSales = sales;
        }
        else if (subscriber == maxSubscriber && sales > maxSales) {
            maxSales = sales;
        }
    }
}
