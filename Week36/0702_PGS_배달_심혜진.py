import heapq

def solution(N, road, K):
    graph = [[] for _ in range(N + 1)]

    for a, b, cost in road:
        graph[a].append((b, cost))
        graph[b].append((a, cost))

    distance = [float('inf')] * (N + 1)
    distance[1] = 0

    pq = [(0, 1)]

    while pq:
        dist, now = heapq.heappop(pq)

        if distance[now] < dist:
            continue

        for next_node, cost in graph[now]:
            new_dist = dist + cost

            if new_dist < distance[next_node]:
                distance[next_node] = new_dist
                heapq.heappush(pq, (new_dist, next_node))

    answer = 0
    for d in distance:
        if d <= K:
            answer += 1

    return answer