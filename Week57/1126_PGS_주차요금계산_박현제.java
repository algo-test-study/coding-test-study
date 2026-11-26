import java.util.*;
class Solution {
    public List<Integer> solution(int[] fees, String[] records) {
        List<Integer> ans = new ArrayList<>();
        Map<String, Integer> d = new HashMap<>();
        Map<String, Integer> total = new TreeMap<>();

        int std_t = fees[0];
        int std_f = fees[1];
        int over_t = fees[2];
        int over_f = fees[3];

        for (String r : records) {
            String[] arr = r.split(" ");

            String time = arr[0];
            String num = arr[1];
            String command = arr[2];

            int curr_m = get_m(time);

            if (command.equals("IN")) {
                d.put(num, curr_m);
            } else {
                int in_m = d.get(num);
                int over = curr_m - in_m;

                total.put(num, total.getOrDefault(num, 0) + over);
                d.remove(num);
            }
        }
        for (String num : d.keySet()) {
            int over = get_m("23:59") - d.get(num);

            total.put(num, total.getOrDefault(num, 0) + over);
        }

        for (int over : total.values()) {
            if (over <= std_t) {
                ans.add(std_f);
            } else {
                ans.add(std_f +
                    (int) Math.ceil((double) (over - std_t) / over_t) * over_f);
            }
        }

        return ans;
    }

    private int get_m(String s) {
        String[] arr = s.split(":");

        int h = Integer.parseInt(arr[0]);
        int m = Integer.parseInt(arr[1]);

        return h * 60 + m;
    }
}
