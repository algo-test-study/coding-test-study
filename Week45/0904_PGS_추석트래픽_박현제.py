""" O(n) """
def solution(lines):
    logs = []

    for line in lines:
        _, time, d = line.split()

        h, m, s = time.split(':')
        h, m, s = int(h), int(m), float(s)
        end = (
            h * 60 * 60 * 1000
            + m * 60 * 1000
            + s * 1000)
    

        d = int(float(d[:-1]) * 1000)

        start = end - d + 1

        logs.append((start, end))

    ans = 0
    points = []

    for start, end in logs:
        points.append(start)
        points.append(end)

    for point in points:
        window_end = point + 999

        cnt = 0
        for start, end in logs:
            if start <= window_end and end >= point:
                cnt += 1

        ans = max(ans, cnt)

    return ans
