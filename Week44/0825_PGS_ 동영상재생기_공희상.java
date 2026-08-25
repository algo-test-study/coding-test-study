import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoLen = toSeconds(video_len);
        int current = toSeconds(pos);
        int opStart = toSeconds(op_start);
        int opEnd = toSeconds(op_end);
        
        current = skipOpening(current, opStart, opEnd);
        for (String command : commands) {
            if (command.equals("prev")) {
                current = Math.max(0, current - 10);
            } else if (command.equals("next")) {
                current = Math.min(videoLen, current + 10);
            }
        
            current = skipOpening(current, opStart, opEnd);
        }
        
        return toTime(current);
    }
    
    private int skipOpening(int current, int opStart, int opEnd) {
        if (opStart <= current && current <= opEnd) {
            return opEnd;
        }
        return current;
    }
    
    private int toSeconds(String time) {
        String[] tokens = time.split(":");
        
        int min = Integer.parseInt(tokens[0]);
        int sec = Integer.parseInt(tokens[1]);
        
        return min * 60 + sec;
    }
    
    private String toTime(int seconds) {
        int min = seconds / 60;
        int sec = seconds % 60;
        
        return String.format("%02d:%02d", min, sec);
    }
}
