import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int initial = n / 3;

        Set<Integer> hand = new HashSet<>();
        Set<Integer> drawn = new HashSet<>();

        for (int i = 0; i < initial; i++) {
            hand.add(cards[i]);
        }

        int round = 1;
        int index = initial;

        while (index < n) {
            drawn.add(cards[index++]);
            drawn.add(cards[index++]);

            boolean success = false;

            for (int card : hand) {
                int pair = n + 1 - card;

                if (hand.contains(pair) && card != pair) {
                    hand.remove(card);
                    hand.remove(pair);

                    success = true;
                    break;
                }
            }

            if (success) {
                round++;
                continue;
            }

            if (coin >= 1) {
                for (int card : hand) {
                    int pair = n + 1 - card;

                    if (drawn.contains(pair)) {
                        hand.remove(card);
                        drawn.remove(pair);

                        coin--;
                        success = true;
                        break;
                    }
                }
            }

            if (success) {
                round++;
                continue;
            }

            if (coin >= 2) {
                for (int card : drawn) {
                    int pair = n + 1 - card;

                    if (drawn.contains(pair) && card != pair) {
                        drawn.remove(card);
                        drawn.remove(pair);

                        coin -= 2;
                        success = true;
                        break;
                    }
                }
            }

            if (!success) {
                break;
            }

            round++;
        }

        return round;
    }
}
