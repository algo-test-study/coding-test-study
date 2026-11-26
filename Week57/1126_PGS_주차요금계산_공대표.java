
import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> inMap = new HashMap<>();
        Map<String, Integer> timeMap = new HashMap<>();

        for (String record : records) {
            String[] arr = record.split(" ");

            String[] time = arr[0].split(":");
            int minute = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);

            String car = arr[1];
            String type = arr[2];

            if (type.equals("IN")) {
                inMap.put(car, minute);
            } else {
                int inTime = inMap.get(car);
                int parkingTime = minute - inTime;

                timeMap.put(car, timeMap.getOrDefault(car, 0) + parkingTime);
                inMap.remove(car);
            }
        }

        for (String car : inMap.keySet()) {
            int parkingTime = 23 * 60 + 59 - inMap.get(car);
            timeMap.put(car, timeMap.getOrDefault(car, 0) + parkingTime);
        }

        List<String> carList = new ArrayList<>(timeMap.keySet());
        Collections.sort(carList);

        int[] answer = new int[carList.size()];

        for (int i = 0; i < carList.size(); i++) {
            int totalTime = timeMap.get(carList.get(i));

            if (totalTime <= fees[0]) {
                answer[i] = fees[1];
            } else {
                int extraTime = totalTime - fees[0];
                int extraFee = (int) Math.ceil((double) extraTime / fees[2]) * fees[3];

                answer[i] = fees[1] + extraFee;
            }
        }

        return answer;
    }
}
