def solution(n, costs):
    costs.sort(key=lambda x: x[2])

    parent = [i for i in range(n)]

    def find(x):
        if parent[x] != x:
            parent[x] = find(parent[x])
        return parent[x]

    def union(a, b):
        ra = find(a)
        rb = find(b)
        if ra == rb:
            return False
        if ra < rb:
            parent[rb] = ra
        else:
            parent[ra] = rb
        return True

    total_cost = 0
    edges_used = 0

    for a, b, cost in costs:
        if union(a, b):
            total_cost += cost
            edges_used += 1
            if edges_used == n - 1:
                break

    return total_cost
