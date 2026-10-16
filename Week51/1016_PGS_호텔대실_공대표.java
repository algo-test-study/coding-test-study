import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0]));

        PriorityQueue<Integer> rooms = new PriorityQueue<>();

        for (String[] book : book_time) {
            int start = Integer.parseInt(book[0].substring(0, 2)) * 60
                      + Integer.parseInt(book[0].substring(3, 5));

            int end = Integer.parseInt(book[1].substring(0, 2)) * 60
                    + Integer.parseInt(book[1].substring(3, 5)) + 10;

            if (!rooms.isEmpty() && rooms.peek() <= start) {
                rooms.poll();
            }

            rooms.offer(end);
        }

        return rooms.size();
    }
}
