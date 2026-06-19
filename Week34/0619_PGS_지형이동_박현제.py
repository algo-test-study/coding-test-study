"""
MST, 크루스칼, O(n^2logn)
"""
def solution(land, height):
    
    answer = 0
    n = len(land)

    root = [i for i in range(n * n)]

    def find(x):
        if root[x] != x:
            root[x] = find(root[x])
        return root[x]

    def union(x, y):
        x = find(a)
        y = find(b)

        if x == y:
            return False

        root[y] = x
        return True

    edges = []

    dx = [1, 0]
    dy = [0, 1]

    for x in range(n):
        for y in range(n):
            curr = x*n + y

            for i in range(2):
                nx = x + dx[i]
                ny = y + dy[i]

                if 0 <= nx < n and 0 <= ny < n:
                    nxt = nx * n + ny
                    diff = abs(land[x][y] - land[nx][ny])

                    if diff <= height:
                        cost = 0
                        
                    else:
                        cost = diff

                    edges.append((cost, curr, nxt))

    edges.sort()
    for cost, a, b in edges:
        if union(a, b):
            answer += cost

    return answer
