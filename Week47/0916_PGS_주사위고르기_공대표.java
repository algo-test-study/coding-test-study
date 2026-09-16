import java.util.*;

class Solution {
    public int[] solution(int[][] dice) {
        int n = dice.length;
        int half = n / 2;

        int[] answer = new int[half];
        int maxWin = 0;

        for (int mask = 0; mask < (1 << n); mask++) {
            if (Integer.bitCount(mask) != half) {
                continue;
            }

            int[] aDice = new int[half];
            int[] bDice = new int[half];

            int aIndex = 0;
            int bIndex = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    aDice[aIndex++] = i;
                } else {
                    bDice[bIndex++] = i;
                }
            }

            int[] aSums = new int[1];
            int[] bSums = new int[1];

            aSums[0] = 0;
            bSums[0] = 0;

            int aCount = 1;
            int bCount = 1;

            for (int i = 0; i < half; i++) {
                int[] nextSums = new int[aCount * 6];

                for (int j = 0; j < aCount; j++) {
                    for (int k = 0; k < 6; k++) {
                        nextSums[j * 6 + k] = aSums[j] + dice[aDice[i]][k];
                    }
                }

                aSums = nextSums;
                aCount *= 6;
            }

            for (int i = 0; i < half; i++) {
                int[] nextSums = new int[bCount * 6];

                for (int j = 0; j < bCount; j++) {
                    for (int k = 0; k < 6; k++) {
                        nextSums[j * 6 + k] = bSums[j] + dice[bDice[i]][k];
                    }
                }

                bSums = nextSums;
                bCount *= 6;
            }

            Arrays.sort(bSums);

            int winCount = 0;

            for (int i = 0; i < aCount; i++) {
                int left = 0;
                int right = bCount;

                while (left < right) {
                    int mid = (left + right) / 2;

                    if (bSums[mid] < aSums[i]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }

                winCount += left;
            }

            if (winCount > maxWin) {
                maxWin = winCount;

                for (int i = 0; i < half; i++) {
                    answer[i] = aDice[i] + 1;
                }
            }
        }

        return answer;
    }
}
