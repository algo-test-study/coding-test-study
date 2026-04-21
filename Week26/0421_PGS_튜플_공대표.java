import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer;

        s = s.substring(2, s.length() - 2);
        String[] arr = s.split("\\},\\{");

        Arrays.sort(arr, (a, b) -> a.length() - b.length());

        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for (String str : arr) {
            String[] nums = str.split(",");

            for (String num : nums) {
                int n = Integer.parseInt(num);
                if (!set.contains(n)) {
                    set.add(n);
                    list.add(n);
                }
            }
        }

        answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
