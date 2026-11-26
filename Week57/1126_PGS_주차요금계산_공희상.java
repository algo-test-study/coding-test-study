import java.util.*;

class Solution {

    public int[] solution(int[] fees, String[] records) {

        // 현재 주차 중인 차량의 입차 시각
        Map<String, Integer> inTimes = new HashMap<>();
        // 차량별 누적 주차 시간 (차량 번호 자동 정렬)
        Map<String, Integer> totalTimes = new TreeMap<>();

        // 모든 입출차 기록 처리
        for (String record : records) {
            String[] parts = record.split(" ");

            int time = toMinutes(parts[0]);
            String car = parts[1];
            String type = parts[2];

            if (type.equals("IN")) {
                // 입차 시각 저장
                inTimes.put(car, time);
            } else {

                // 입차 시각을 꺼내고 제거
                int inTime = inTimes.remove(car);

                // 누적 주차 시간 갱신
                int duration = time - inTime;

                totalTimes.merge(car, duration, Integer::sum);
            }
        }

        // 출차하지 않은 차량은 23:59 출차 처리
        int endOfDay = 23 * 60 + 59;

        for (Map.Entry<String, Integer> entry : inTimes.entrySet()) {
            String car = entry.getKey();
            int inTime = entry.getValue();

            int duration = endOfDay - inTime;

            totalTimes.merge(car, duration, Integer::sum);
        }

        // 차량 번호 오름차순으로 요금 계산
        int[] answer = new int[totalTimes.size()];
        int index = 0;

        for (int total : totalTimes.values()) {
            answer[index++] = calculateFee(total, fees);
        }

        return answer;
    }

    // 분 단위 변환
    private int toMinutes(String time) {
        String[] parts = time.split(":");

        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        return hour * 60 + minute;
    }

    // 누적 주차 시간에 따른 요금 계산
    private int calculateFee(int total, int[] fees) {
        int baseTime = fees[0];
        int baseFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        if (total <= baseTime) {
            return baseFee;
        }

        int extraTime = total - baseTime;

        // 올림 나눗셈
        int units = (extraTime + unitTime - 1) / unitTime;

        return baseFee + units * unitFee;
    }
}
