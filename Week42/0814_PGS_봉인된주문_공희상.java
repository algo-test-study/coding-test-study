import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        long[] nums = new long[bans.length];

        for (int i = 0; i < bans.length; i++) {
            nums[i] = toNumber(bans[i]);
        }

        Arrays.sort(nums);

        for (long ban : nums) {
            if (ban <= n) {
                n++;
            } else {
                break;
            }
        }

        return toString(n);
    }

    private long toNumber(String s) {
        long num = 0;

        for (char c : s.toCharArray()) {
            num = num * 26 + (c - 'a' + 1);
        }

        return num;
    }

    private String toString(long num) {
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            num--;
            sb.append((char) ('a' + num % 26));
            num /= 26;
        }

        return sb.reverse().toString();
    }
}
