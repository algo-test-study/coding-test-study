def solution(tickets):
    graph = {}

    for from_airport, to_airport in tickets:
        if from_airport not in graph:
            graph[from_airport] = []
        graph[from_airport].append(to_airport)

    for from_airport in graph:
        graph[from_airport].sort(reverse=True)

    stack = ["ICN"]
    route = []

    while stack:
        current = stack[-1]

        if current in graph and graph[current]:
            next_airport = graph[current].pop()
            stack.append(next_airport)
        else:
            route.append(stack.pop())

    return route[::-1]
