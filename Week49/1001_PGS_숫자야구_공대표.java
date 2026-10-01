import java.util.*;
import java.util.function.Function;

class Solution {
    public int solution(int n, Function<Integer, String> submit) {
        List<Integer> candidates = new ArrayList<>();

        for (int number = 1000; number <= 9999; number++) {
            String s = String.valueOf(number);

            if (s.charAt(0) == s.charAt(1)
                    || s.charAt(0) == s.charAt(2)
                    || s.charAt(0) == s.charAt(3)
                    || s.charAt(1) == s.charAt(2)
                    || s.charAt(1) == s.charAt(3)
                    || s.charAt(2) == s.charAt(3)) {
                continue;
            }

            if (s.indexOf('0') >= 0) {
                continue;
            }

            candidates.add(number);
        }

        int[] digits = new int[10000];
        int[] masks = new int[10000];

        for (int number : candidates) {
            String s = String.valueOf(number);

            int mask = 0;

            for (int i = 0; i < 4; i++) {
                int digit = s.charAt(i) - '0';
                mask |= 1 << digit;
            }

            digits[number] = mask;
        }

        for (int attempt = 0; attempt < n; attempt++) {
            if (candidates.size() == 1) {
                return candidates.get(0);
            }

            int bestGuess = candidates.get(0);
            int bestWorst = Integer.MAX_VALUE;

            for (int guess : candidates) {
                int[] count = new int[25];
                int worst = 0;

                for (int password : candidates) {
                    int strike = 0;

                    int g = guess;
                    int p = password;

                    int g0 = g / 1000;
                    int g1 = g / 100 % 10;
                    int g2 = g / 10 % 10;
                    int g3 = g % 10;

                    int p0 = p / 1000;
                    int p1 = p / 100 % 10;
                    int p2 = p / 10 % 10;
                    int p3 = p % 10;

                    if (g0 == p0) {
                        strike++;
                    }
                    if (g1 == p1) {
                        strike++;
                    }
                    if (g2 == p2) {
                        strike++;
                    }
                    if (g3 == p3) {
                        strike++;
                    }

                    int common = Integer.bitCount(digits[guess] & digits[password]);
                    int ball = common - strike;

                    int result = strike * 5 + ball;

                    count[result]++;

                    if (count[result] > worst) {
                        worst = count[result];
                    }
                }

                if (worst < bestWorst) {
                    bestWorst = worst;
                    bestGuess = guess;
                }
            }

            String hint = submit.apply(bestGuess);

            int strike = hint.charAt(0) - '0';
            int ball = hint.charAt(3) - '0';

            int result = strike * 5 + ball;

            List<Integer> nextCandidates = new ArrayList<>();

            for (int password : candidates) {
                int g0 = bestGuess / 1000;
                int g1 = bestGuess / 100 % 10;
                int g2 = bestGuess / 10 % 10;
                int g3 = bestGuess % 10;

                int p0 = password / 1000;
                int p1 = password / 100 % 10;
                int p2 = password / 10 % 10;
                int p3 = password % 10;

                int currentStrike = 0;

                if (g0 == p0) {
                    currentStrike++;
                }
                if (g1 == p1) {
                    currentStrike++;
                }
                if (g2 == p2) {
                    currentStrike++;
                }
                if (g3 == p3) {
                    currentStrike++;
                }

                int common = Integer.bitCount(digits[bestGuess] & digits[password]);
                int currentBall = common - currentStrike;

                if (currentStrike * 5 + currentBall == result) {
                    nextCandidates.add(password);
                }
            }

            if (strike == 4) {
                return bestGuess;
            }

            candidates = nextCandidates;
        }

        return candidates.get(0);
    }
}
