import java.util.*;

class Immigration {
    private final int people;
    private final int[] times;

    private Immigration(int people, int[] times) {
        this.people = people;
        Arrays.sort(times);
        this.times = times;
    }

    public static Immigration of (int people, int[] times) {
        return new Immigration(people, times);
    }

    public long getMinScreeningTime() {
        long left = 1;//Minimum Judge Time Init: 1
        long right = (long) times[times.length - 1] * people;//Maximum Judge Time: Max time * Number of People
        long result = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            long total = 0;
            for (int time : times) {
                total += mid / time;
                if (isScreened(total, people)) {
                    break;
                }
            }

            if (isScreened(total, people)) {
                result = mid;
                right = mid -1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    private boolean isScreened(long screenedPeople, long totalPeople) {
        return Long.compare(screenedPeople, totalPeople) >= 0;
    }
}

class Solution {
    public long solution(int n, int[] times) {
        return Immigration.of(n, times).getMinScreeningTime();
    }
}
