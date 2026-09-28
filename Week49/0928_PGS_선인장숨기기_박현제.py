from collections import deque
def solution(m, n, h, w, drops):
    INF = len(drops) + 1

    rain = [[INF] * n for _ in range(m)]

    for order in range(len(drops)):
        cy = drops[order][0]
        cx = drops[order][1]

        rain[cy][cx] = order + 1

    width = n - w + 1
    row_min = [[0] * width for _ in range(m)]

    for cy in range(m):
        dq = deque()

        for cx in range(n):
            while dq and rain[cy][dq[-1]] >= rain[cy][cx]:
                dq.pop()

            dq.append(cx)

            while dq and dq[0] <= cx - w:
                dq.popleft()

            if cx >= w - 1:
                start_cx = cx - w + 1
                row_min[cy][start_cx] = rain[cy][dq[0]]

    min_value = -1
    min_cy = m
    min_cx = n

    for cx in range(width):
        dq = deque()

        for cy in range(m):
            while dq and row_min[dq[-1]][cx] >= row_min[cy][cx]:
                dq.pop()

            dq.append(cy)

            while dq and dq[0] <= cy - h:
                dq.popleft()

            if cy >= h - 1:
                start_cy = cy - h + 1
                value = row_min[dq[0]][cx]

                if value > min_value:
                    min_value = value
                    min_cy = start_cy
                    min_cx = cx

                elif value == min_value:
                    if start_cy < min_cy:
                        min_cy = start_cy
                        min_cx = cx

                    elif start_cy == min_cy and cx < min_cx:
                        min_cx = cx
    ans =  [min_cy, min_cx]
    return ans
