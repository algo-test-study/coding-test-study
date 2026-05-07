import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};

        ArrayList<Integer> arrayList = new ArrayList<>();
        HashMap<String, Integer> map = termsToMap(terms);

        for (int i = 0; i < privacies.length; i++) {
            boolean result = judgePrivacy(today, privacies[i], map);

            if (result) {
                arrayList.add(i + 1);
            }
        }

        answer = new int[arrayList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = arrayList.get(i);
        }

        return answer;
    }

    public static boolean judgePrivacy(String today, String privacy, HashMap<String, Integer> map) {
        String[] str = privacy.split(" ");

        String[] splitPrivacy = str[0].split("\\.");
        Integer i = map.get(str[1]);

        int[] intPrivacy = Arrays.stream(splitPrivacy).mapToInt(Integer::parseInt).toArray();
        intPrivacy[1] += i;
        if (intPrivacy[1] > 12) {
            intPrivacy[0] += intPrivacy[1] / 12;
            intPrivacy[1] = intPrivacy[1] % 12;
        }

        intPrivacy[2] -= 1;

        if (intPrivacy[2] == 0) {
            intPrivacy[2] = 28;
            intPrivacy[1] -= 1;

            if (intPrivacy[1] == 0) {
                intPrivacy[1] = 12;
                intPrivacy[2] -= 1;
            }
        }



        Date date1 = new Date(intPrivacy[0], intPrivacy[1], intPrivacy[2]);

        String[] splitToday = today.split("\\.");
        int[] intToday = Arrays.stream(splitToday).mapToInt(Integer::parseInt).toArray();
        Date date2 = new Date(intToday[0], intToday[1], intToday[2]);


        return date1.before(date2);
    }

    public static HashMap<String, Integer> termsToMap(String[] terms) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String term : terms) {
            String[] str = term.split(" ");

            map.put(str[0], Integer.parseInt(str[1]));
        }

        return map;
    }
}
