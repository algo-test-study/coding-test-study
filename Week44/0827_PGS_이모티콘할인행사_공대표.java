class Solution {
    int[] discounts = {10, 20, 30, 40};
    int[] answer = {0, 0};

    public int[] solution(int[][] users, int[] emoticons) {
        dfs(0, new int[emoticons.length], users, emoticons);
        return answer;
    }

    public void dfs(int index, int[] discount, int[][] users, int[] emoticons) {
        if (index == emoticons.length) {
            int plus = 0;
            int sales = 0;

            for (int[] user : users) {
                int userDiscount = user[0];
                int userPrice = user[1];
                int total = 0;

                for (int i = 0; i < emoticons.length; i++) {
                    if (discount[i] >= userDiscount) {
                        total += emoticons[i] * (100 - discount[i]) / 100;
                    }
                }

                if (total >= userPrice) {
                    plus++;
                } else {
                    sales += total;
                }
            }

            if (plus > answer[0]) {
                answer[0] = plus;
                answer[1] = sales;
            } else if (plus == answer[0] && sales > answer[1]) {
                answer[1] = sales;
            }

            return;
        }

        for (int i = 0; i < 4; i++) {
            discount[index] = discounts[i];
            
            dfs(index + 1, discount, users, emoticons);
        }
    }
}
