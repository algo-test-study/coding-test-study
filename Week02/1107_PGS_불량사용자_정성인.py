def is_banned(id, masked_id):
    if len(id) != len(masked_id):
        return False
    
    for i in range(len(id)):
        if masked_id[i] == '*':
            continue
        if id[i] != masked_id[i]:
            return False
    return True
result = set()

def dfs(depth, len_banned, visited, banned):
    global result
    
    if depth == len_banned:
        case = tuple(sorted([i for i, j in enumerate(visited) if j]))
        result.add(case)
        return
    
    if not banned[depth]:
        return
    
    for idx in banned[depth]:
        if visited[idx]:
            continue
        visited[idx] = True
        dfs(depth+1, len_banned, visited, banned)
        visited[idx] = False

def solution(user_id, banned_id):
    banned = [[] for _ in range(len(banned_id))]
    
    for i, masked in enumerate(banned_id):
        for j, id in enumerate(user_id):
            if is_banned(id, masked):
                banned[i].append(j)
    dfs(0, len(banned_id), [False]*len(user_id), banned)
    return len(result)
