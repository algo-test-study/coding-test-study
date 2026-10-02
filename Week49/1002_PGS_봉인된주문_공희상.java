import java.util.*;

class Solution {

    public String solution(long n, String[] bans) {
        long[] banNumbers = new long[bans.length];

        for (int i = 0; i < bans.length; i++) {
            banNumbers[i] = toNumber(bans[i]);
        }

        Arrays.sort(banNumbers);

        for (long ban : banNumbers) {
            if (ban <= n) {
                n++;
            } else {
                break;
            }
        }

        return toString(n);
    }

    private long toNumber(String str) {
        long number = 0;

        for (char c : str.toCharArray()) {
            number = number * 26 + (c - 'a' + 1);
        }

        return number;
    }

    private String toString(long number) {
        StringBuilder sb = new StringBuilder();

        while (number > 0) {
            number--;

            int remainder = (int) (number % 26);
            sb.append((char) ('a' + remainder));

            number /= 26;
        }

        return sb.reverse().toString();
    }
}
