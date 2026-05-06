import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        Map<Integer, Integer> in = new HashMap<>();
        Map<Integer, Integer> out = new HashMap<>();

        for (int[] e : edges) {
            int from = e[0];
            int to = e[1];

            out.put(from, out.getOrDefault(from, 0) + 1);
            in.put(to, in.getOrDefault(to, 0) + 1);

            in.putIfAbsent(from, in.getOrDefault(from, 0));
            out.putIfAbsent(to, out.getOrDefault(to, 0));
        }

        int start = 0;

        for (int node : out.keySet()) {
            int o = out.getOrDefault(node, 0);
            int i = in.getOrDefault(node, 0);

            if (o >= 2 && i == 0) {
                start = node;
                break;
            }
        }

        int donut = 0;
        int stick = 0;
        int eight = 0;

        for (int node : out.keySet()) {
            if (node == start) continue;

            int o = out.getOrDefault(node, 0);
            int i = in.getOrDefault(node, 0);

            if (o == 0) {
                stick++;
            } else if (o >= 2) {
                eight++;
            }
        }

        int total = out.get(start);
        donut = total - stick - eight;

        answer[0] = start;
        answer[1] = donut;
        answer[2] = stick;
        answer[3] = eight;

        return answer;
    }
}
