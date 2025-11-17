import heapq

def dijkstra(graph, start):
    INF = float('inf')
    distance = [INF] * (len(graph))
    distance[start] = 0
    hq = [(0, start)]
    
    while hq:
        dist, node = heapq.heappop(hq)
        
        if distance[node] < dist:
            continue
        
        for nxt_node, w in graph[node]:
            nxt_dist = dist+w
            
            if nxt_dist < distance[nxt_node]:
                distance[nxt_node] = nxt_dist
                heapq.heappush(hq, (nxt_dist, nxt_node))
    return distance

def solution(N, road, K):
    answer = 0
    graph= [[] for _ in range(N+1)]
    
    for a, b, c in road:
        graph[a].append((b, c))
        graph[b].append((a, c))

    distance = dijkstra(graph, 1)
    
    for dist in distance:
        if dist <= K:
            answer += 1

    return answer
