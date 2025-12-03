
"""
알고리즘
- dfs
- 경로 리스트에 넣기 -> 정렬
"""
def solution(tickets):
    
    routes = []
    length = len(tickets)
    visited = [False]*length

    def dfs(s, route, depth):
        if depth == length:
            routes.append(route)
            return

        for i in range(length):
            if not visited[i] and tickets[i][0] == s:
                visited[i] = True
                dfs(tickets[i][1], route + " " + tickets[i][1], depth + 1)
                visited[i] = False
    dfs("ICN", "ICN", 0)

    routes.sort()
    return routes[0].split(" ")
