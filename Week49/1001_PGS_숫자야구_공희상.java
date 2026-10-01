import java.util.*;
import java.util.function.Function;

class Solution {

    public int solution(int n, Function<Integer, String> submit) {

        List<Integer> candidates = createCandidates();

        int result1234 = parse(submit.apply(1234));

        if (result1234 == 20) {
            return 1234;
        }

        candidates = filter(candidates, 1234, result1234);

        int result5678 = parse(submit.apply(5678));

        if (result5678 == 20) {
            return 5678;
        }

        candidates = filter(candidates, 5678, result5678);

        while (candidates.size() > 1) {

            int guess = findBestGuess(candidates);

            int result = parse(submit.apply(guess));

            if (result == 20) {
                return guess;
            }

            candidates = filter(candidates, guess, result);
        }

        return candidates.get(0);
    }

    private List<Integer> createCandidates() {

        List<Integer> candidates = new ArrayList<>();

        for (int a = 1; a <= 9; a++) {
            for (int b = 1; b <= 9; b++) {

                if (a == b) {
                    continue;
                }

                for (int c = 1; c <= 9; c++) {

                    if (c == a || c == b) {
                        continue;
                    }

                    for (int d = 1; d <= 9; d++) {

                        if (d == a || d == b || d == c) {
                            continue;
                        }

                        candidates.add(
                                a * 1000
                                + b * 100
                                + c * 10
                                + d
                        );
                    }
                }
            }
        }

        return candidates;
    }

    private int findBestGuess(List<Integer> candidates) {

        int bestGuess = candidates.get(0);
        int minWorst = Integer.MAX_VALUE;

        for (int guess : candidates) {

            int[] groups = new int[25];

            for (int secret : candidates) {
                int result = score(guess, secret);
                groups[result]++;
            }

            int worst = 0;

            for (int count : groups) {
                worst = Math.max(worst, count);
            }

            if (worst < minWorst) {
                minWorst = worst;
                bestGuess = guess;
            }
        }

        return bestGuess;
    }

    private List<Integer> filter(
            List<Integer> candidates,
            int guess,
            int result
    ) {

        List<Integer> filtered = new ArrayList<>();

        for (int candidate : candidates) {

            if (score(guess, candidate) == result) {
                filtered.add(candidate);
            }
        }

        return filtered;
    }

    private int score(int guess, int secret) {

        int[] g = toDigits(guess);
        int[] s = toDigits(secret);

        int strike = 0;
        int ball = 0;

        for (int i = 0; i < 4; i++) {

            if (g[i] == s[i]) {
                strike++;
                continue;
            }

            for (int j = 0; j < 4; j++) {

                if (g[i] == s[j]) {
                    ball++;
                    break;
                }
            }
        }

        return strike * 5 + ball;
    }

    private int[] toDigits(int number) {
        return new int[]{
                number / 1000,
                number / 100 % 10,
                number / 10 % 10,
                number % 10
        };
    }

    private int parse(String result) {

        int strike = result.charAt(0) - '0';
        int ball = result.charAt(3) - '0';

        return strike * 5 + ball;
    }
}
