public long solution(int n, int[] times) {
        long answer = 0;
        long left = 1L;

        long maxTime = 0L;
        for (int t : times) {
            if (t > maxTime) maxTime = t;
        }

        long right = maxTime * (long) n; // 최악의 경우

        answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;

            long processed = 0L;
            for (int t : times) {
                processed += mid / t;

                if (processed >= n) break;
            }

            if (processed >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }
