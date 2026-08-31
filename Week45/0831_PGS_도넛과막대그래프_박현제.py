def solution(edges):
    max_v = max(max(a, b) for a, b in edges)

    ind = [0] * (max_v + 1)
    out = [0] * (max_v + 1)

    answer = [0, 0, 0, 0]

    for a, b in edges:
        out[a] += 1
        ind[b] += 1

    for node in range(1, max_v + 1):
        if out[node] >= 2:
            if ind[node] == 0:
                answer[0] = node
            elif ind[node] >= 2:
                answer[3] += 1

    for node in range(1, max_v + 1):
        if ind[node] >= 1 and out[node] == 0:
            answer[2] += 1

    answer[1] = out[answer[0]] - answer[2] - answer[3]

    return answer
