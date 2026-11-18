class Solution {

    private final int[] discounts = {10, 20, 30, 40};

    private int[][] users;
    private int[] emoticons;
    private int[] selected;

    private int bestSubscribers;
    private int bestSales;

    public int[] solution(int[][] users, int[] emoticons) {

        this.users = users;
        this.emoticons = emoticons;
        this.selected = new int[emoticons.length];

        bestSubscribers = 0;
        bestSales = 0;

        dfs(0);

        return new int[]{bestSubscribers, bestSales};
    }

    private void dfs(int depth) {

        if (depth == emoticons.length) {
            evaluate();
            return;
        }

        for (int rate : discounts) {
            selected[depth] = rate;
            dfs(depth + 1);
        }
    }

    private void evaluate() {

        int subscribers = 0;
        int sales = 0;

        for (int[] user : users) {

            int minDiscount = user[0];
            int minPrice = user[1];

            int total = 0;

            for (int i = 0; i < emoticons.length; i++) {

                if (selected[i] < minDiscount) {
                    continue;
                }

                int price = emoticons[i]
                        * (100 - selected[i]) / 100;

                total += price;
            }

            if (total >= minPrice) {
                subscribers++;
            } else {
                sales += total;
            }
        }

        if (subscribers > bestSubscribers ||
            (subscribers == bestSubscribers &&
             sales > bestSales)) {

            bestSubscribers = subscribers;
            bestSales = sales;
        }
    }
}
