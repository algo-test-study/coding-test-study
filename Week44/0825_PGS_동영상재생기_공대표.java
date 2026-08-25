class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int video = toSecond(video_len);
        int current = toSecond(pos);
        int start = toSecond(op_start);
        int end = toSecond(op_end);

        for (String command : commands) {
            if (current >= start && current <= end) {
                current = end;
            }

            if (command.equals("prev")) {
                current = Math.max(0, current - 10);
            } else {
                current = Math.min(video, current + 10);
            }

            if (current >= start && current <= end) {
                current = end;
            }
        }

        return String.format("%02d:%02d", current / 60, current % 60);
    }

    public int toSecond(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}
