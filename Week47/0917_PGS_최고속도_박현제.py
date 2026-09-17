import heapq

def solution(city, road):
    INF = float('inf')
    node_id = {}
    limit = []

    def get(p):
        if p not in node_id:
            node_id[p] = len(limit)
            limit.append(INF)
        return node_id[p]

    m = len(road)
    points = [[] for _ in range(m)]
    H, V = [], []

    for i, (x1, y1, x2, y2, lim) in enumerate(road):
        a = get((x1, y1)); b = get((x2, y2))
        mid = get(((x1 + x2) // 2, (y1 + y2) // 2))
        limit[mid] = min(limit[mid], lim)
        points[i] += [(x1, y1), (x2, y2), ((x1 + x2) // 2, (y1 + y2) // 2)]
        if y1 == y2 and x1 != x2:
            H.append(i)
        else:
            V.append(i)

    for hi in H:
        hx1, hy, hx2 = road[hi][0], road[hi][1], road[hi][2]
        for vi in V:
            vx, vy1, vy2 = road[vi][0], road[vi][1], road[vi][3]
            if hx1 <= vx <= hx2 and vy1 <= hy <= vy2:
                p = (vx, hy)
                get(p)
                points[hi].append(p)
                points[vi].append(p)

    for cx, cy in city:
        get((cx, cy))
        for i, (x1, y1, x2, y2, _) in enumerate(road):
            if x1 <= cx <= x2 and y1 <= cy <= y2:
                points[i].append((cx, cy))

    n = len(limit)
    node = [[] for _ in range(n)]
    for i in range(m):
        ps = sorted(set(points[i]))
        for j in range(len(ps) - 1):
            u = node_id[ps[j]]; v = node_id[ps[j + 1]]
            node[u].append(v)
            node[v].append(u)

    # 최대 병목 경로 (변형 다익스트라)
    start = node_id[tuple(city[0])]
    dist = [-1] * n
    dist[start] = limit[start]
    pq = [(-dist[start], start)]
    while pq:
        d, u = heapq.heappop(pq)
        d = -d
        if d < dist[u]:
            continue
        for v in node[u]:
            nv = min(d, limit[v])
            if nv > dist[v]:
                dist[v] = nv
                heapq.heappush(pq, (-nv, v))

    ans = []
    for cx, cy in city[1:]:
        r = dist[node_id[(cx, cy)]]
        ans.append(0 if r == INF else r)
        
    return ans
