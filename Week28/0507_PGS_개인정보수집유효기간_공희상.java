import java.util.*;

class Solution {
    StringTokenizer st;
    
    int dateToDays(String date) {
        st = new StringTokenizer(date, ".");
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());

        return year * 12 * 28 + month * 28 + day;
    }
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termMap = new HashMap<>();
        
        for (String term : terms) {
            st = new StringTokenizer(term);
            termMap.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }
        
        int todayDays = dateToDays(today);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            st = new StringTokenizer(privacies[i]);
            String date = st.nextToken();
            String type = st.nextToken();

            int collectedDays = dateToDays(date);
            int expireDays = collectedDays + termMap.get(type) * 28 - 1;

            if (expireDays < todayDays) {
                result.add(i + 1);
            }
        }
        
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}
