import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        List<Long>[] list = new ArrayList[12];

        for (int i = 1; i <= 11; i++) {
            list[i] = new ArrayList<>();
        }

        for (String ban : bans) {
            long value = 0;

            for (int i = 0; i < ban.length(); i++) {
                value = value * 26 + (ban.charAt(i) - 'a');
            }

            list[ban.length()].add(value);
        }

        for (int i = 1; i <= 11; i++) {
            Collections.sort(list[i]);
        }

        int length = 1;

        while (true) {
            long total = 1;

            for (int i = 0; i < length; i++) {
                total *= 26;
            }

            long count = total - list[length].size();

            if (n > count) {
                n -= count;
                length++;
            } else {
                break;
            }
        }

        List<Long> banned = list[length];

        long left = 0;
        long right = 1;

        for (int i = 0; i < length; i++) {
            right *= 26;
        }

        right--;

        while (left < right) {
            long mid = left + (right - left) / 2;

            int low = 0;
            int high = banned.size();

            while (low < high) {
                int middle = (low + high) / 2;

                if (banned.get(middle) <= mid) {
                    low = middle + 1;
                } else {
                    high = middle;
                }
            }

            long available = mid + 1 - low;

            if (available >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        long value = left;

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            answer.append((char) ('a' + value % 26));
            value /= 26;
        }

        return answer.reverse().toString();
    }
}
