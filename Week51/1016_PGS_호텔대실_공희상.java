import java.util.*;

class Solution {

    public int solution(String[][] book_time) {

        int[][] bookings = new int[book_time.length][2];

        for (int i = 0; i < book_time.length; i++) {
            bookings[i][0] = toMinutes(book_time[i][0]);
            bookings[i][1] = toMinutes(book_time[i][1]);
        }

        Arrays.sort(bookings, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<Integer> rooms = new PriorityQueue<>();

        for (int[] booking : bookings) {
            int start = booking[0];
            int end = booking[1] + 10;

            if (!rooms.isEmpty() && rooms.peek() <= start) {
                rooms.poll();
            }

            rooms.offer(end);
        }

        return rooms.size();
    }

    private int toMinutes(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3, 5));

        return hour * 60 + minute;
    }
}
