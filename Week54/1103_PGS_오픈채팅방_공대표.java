import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();

        for (String r : record) {
            String[] split = r.split(" ");
            String cmd = split[0];
            String uid = split[1];

            if (cmd.equals("Enter") || cmd.equals("Change")) {
                String nick = split[2];
                map.put(uid, nick);
            }
        }

        List<String> result = new ArrayList<>();

        for (String r : record) {
            String[] split = r.split(" ");
            String cmd = split[0];
            String uid = split[1];

            if (cmd.equals("Enter")) {
                result.add(map.get(uid) + "님이 들어왔습니다.");
            } else if (cmd.equals("Leave")) {
                result.add(map.get(uid) + "님이 나갔습니다.");
            }
        }

        return result.toArray(new String[0]);
    }
}
