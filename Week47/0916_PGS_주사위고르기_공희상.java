import java.util.*;

class Solution {

    private int[][] dice;
    private int n;
    private long maxWin = -1;
    private int[] answer;

    public int[] solution(int[][] dice) {
        this.dice = dice;
        this.n = dice.length;

        combination(0, 0, new int[n / 2]);

        return answer;
    }

    private void combination(int start, int depth, int[] selected) {
        if (depth == n / 2) {
            calculate(selected);
            return;
        }

        for (int i = start; i < n; i++) {
            selected[depth] = i;
            combination(i + 1, depth + 1, selected);
        }
    }

    private void calculate(int[] selected) {
        boolean[] isA = new boolean[n];

        for (int index : selected) {
            isA[index] = true;
        }

        int[] aDice = new int[n / 2];
        int[] bDice = new int[n / 2];

        int aIndex = 0;
        int bIndex = 0;

        for (int i = 0; i < n; i++) {
            if (isA[i]) {
                aDice[aIndex++] = i;
            } else {
                bDice[bIndex++] = i;
            }
        }

        List<Integer> aSums = new ArrayList<>();
        List<Integer> bSums = new ArrayList<>();

        makeSums(aDice, 0, 0, aSums);
        makeSums(bDice, 0, 0, bSums);

        Collections.sort(bSums);

        long win = 0;

        for (int aSum : aSums) {
            win += lowerBound(bSums, aSum);
        }

        if (win > maxWin) {
            maxWin = win;

            answer = new int[n / 2];

            for (int i = 0; i < selected.length; i++) {
                answer[i] = selected[i] + 1;
            }
        }
    }

    private void makeSums(
        int[] selectedDice,
        int depth,
        int sum,
        List<Integer> sums
    ) {
        if (depth == selectedDice.length) {
            sums.add(sum);
            return;
        }

        int diceIndex = selectedDice[depth];

        for (int value : dice[diceIndex]) {
            makeSums(
                selectedDice,
                depth + 1,
                sum + value,
                sums
            );
        }
    }

    private int lowerBound(List<Integer> arr, int target) {
        int left = 0;
        int right = arr.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
