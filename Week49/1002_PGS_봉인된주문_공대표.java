import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        long[] bannedNumbers = new long[bans.length];

        for (int i = 0; i < bans.length; i++) {
            long number = 0;

            for (int j = 1; j < bans[i].length(); j++) {
                long power = 1;

                for (int k = 0; k < j; k++) {
                    power *= 26;
                }

                number += power;
            }

            for (int j = 0; j < bans[i].length(); j++) {
                long power = 1;

                for (int k = 0; k < bans[i].length() - 1 - j; k++) {
                    power *= 26;
                }

                number += (bans[i].charAt(j) - 'a') * power;
            }

            bannedNumbers[i] = number + 1;
        }

        Arrays.sort(bannedNumbers);

        long left = 1;
        long right = 0;

        for (int i = 1; i <= 11; i++) {
            long power = 1;

            for (int j = 0; j < i; j++) {
                power *= 26;
            }

            right += power;
        }

        while (left < right) {
            long mid = (left + right) / 2;

            int low = 0;
            int high = bannedNumbers.length;

            while (low < high) {
                int middle = (low + high) / 2;

                if (bannedNumbers[middle] <= mid) {
                    low = middle + 1;
                } else {
                    high = middle;
                }
            }

            long deleted = low;
            long alive = mid - deleted;

            if (alive >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        long number = left;
        int length = 1;

        while (true) {
            long power = 1;

            for (int i = 0; i < length; i++) {
                power *= 26;
            }

            if (number <= power) {
                break;
            }

            number -= power;
            length++;
        }
        number--;

        char[] answer = new char[length];

        for (int i = length - 1; i >= 0; i--) {
            answer[i] = (char) ('a' + number % 26);
            number /= 26;
        }

        return new String(answer);
    }
}
