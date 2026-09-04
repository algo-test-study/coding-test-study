import java.util.*;

class Solution {
    public int solution(String[] lines) {
        int n = lines.length;
        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; i++) {
            String[] s = lines[i].split(" ");
            String[] time = s[1].split(":");

            int h = Integer.parseInt(time[0]);
            int m = Integer.parseInt(time[1]);
            int sec = Integer.parseInt(time[2].substring(0, 2));
            int ms = Integer.parseInt(time[2].substring(3));

            end[i] = (h * 3600 + m * 60 + sec) * 1000 + ms;

            String t = s[2].replace("s", "");
            double d = Double.parseDouble(t);
            int duration = (int) (d * 1000);

            start[i] = end[i] - duration + 1;
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            int count = 0;
            int windowStart = end[i];
            int windowEnd = windowStart + 999;

            for (int j = 0; j < n; j++) {
                if (start[j] <= windowEnd && end[j] >= windowStart) {
                    count++;
                }
            }

            answer = Math.max(answer, count);
        }

        return answer;
    }
