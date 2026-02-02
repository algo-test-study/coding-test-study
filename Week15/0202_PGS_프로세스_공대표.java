import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Integer[]> queue = new LinkedList<>();
        Integer[] arr = new Integer[priorities.length];

        for (int i = 0; i < priorities.length; i++) {
            arr[i] = priorities[i];
        }

        Arrays.sort(arr, Comparator.reverseOrder());

        for (int i = 0; i < priorities.length; i++) {
            queue.add(new Integer[]{i, priorities[i]});
        }

        int index = 0;
        while (!queue.isEmpty()) {
            Integer[] poll = queue.poll();

            if (poll[1] == arr[index]) {
                if (poll[0] == location) {
                    answer = index + 1;
                    break;
                }

                index++;
            } else {
                queue.add(poll);
            }
        }

        return answer;
    }
}
