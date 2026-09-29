#이분탐색
def solution(diffs, times, limit):
    start = 1
    end = max(diffs)

    while start < end:
        mid = (start + end) // 2

        total = times[0]

        for i in range(1, len(diffs)):
            diff = diffs[i]
            time_cur = times[i]
            time_prev = times[i - 1]

            if diff <= mid:
                total += time_cur
            else:
                re = diff - mid
                total += (time_cur + time_prev) * re + time_cur

            if total > limit:
                break

        if total <= limit:
            end = mid
        else:
            start = mid + 1

    return start
