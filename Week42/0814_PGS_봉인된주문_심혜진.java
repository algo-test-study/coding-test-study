import java.util.*;

class Solution {
    static final int MAX_LEN = 11;
    static long[] pow26 = new long[MAX_LEN + 1];

    public String solution(long n, String[] bans) {
        pow26[0] = 1L;
        for (int i = 1; i <= MAX_LEN; i++) {
            pow26[i] = pow26[i - 1] * 26L;
        }

        List<String>[] byLen = new ArrayList[MAX_LEN + 1];
        for (int i = 0; i <= MAX_LEN; i++) {
            byLen[i] = new ArrayList<>();
        }
        for (String s : bans) {
            if (s.length() <= MAX_LEN) {
                byLen[s.length()].add(s);
            }
        }
        for (int len = 1; len <= MAX_LEN; len++) {
            Collections.sort(byLen[len]);
        }

        for (int len = 1; len <= MAX_LEN; len++) {
            long total = pow26[len];
            long bannedCount = byLen[len].size();
            long valid = total - bannedCount;
            if (n > valid) {
                n -= valid;
            } else {
                return kthValidOfLength(len, n, byLen[len]);
            }
        }
        return "";
    }

    private String kthValidOfLength(int len, long k, List<String> bannedList) {
        String[] bans = bannedList.toArray(new String[0]);
        StringBuilder sb = new StringBuilder();
        for (int pos = 0; pos < len; pos++) {
            for (char c = 'a'; c <= 'z'; c++) {
                sb.append(c);
                long cnt = countValidWithPrefix(len, sb.toString(), bans);
                if (k > cnt) {
                    k -= cnt;
                    sb.setLength(sb.length() - 1);
                } else {
                    break;
                }
            }
        }
        return sb.toString();
    }

    private long countValidWithPrefix(int len, String prefix, String[] bans) {
        int remaining = len - prefix.length();
        long total = pow26[remaining];
        if (bans.length == 0) return total;

        String start = prefix;
        String end = prefix + '{';

        int l = lowerBound(bans, start);
        int r = lowerBound(bans, end);
        long bannedInSubtree = r - l;
        return total - bannedInSubtree;
    }

    private int lowerBound(String[] arr, String key) {
        int lo = 0;
        int hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid].compareTo(key) < 0) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
