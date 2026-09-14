
def solution(n, costs):
    root = list(range(n))

    def find(x):
        if root[x] != x:
            root[x] = find(root[x])
        return root[x]

    def union(a, b):
        a = find(a)
        b = find(b)

        if a == b:
            return False

        parent[b] = a
        return True

    costs.sort(key=lambda x: x[2])

    answer = 0
    cnt = 0

    for a, b, cost in costs:
        if union(a, b):
            answer += cost
            cnt += 1

            if cnt == n - 1:
                break

    return answer
