"""
dfs, 백트래킹, O(8!)

"""
def solution(user_id, banned_id):
    answer = 0
    
    u_len = len(user_id)
    b_len = len(banned_id)
    
    def is_valid(user, banned):
        if len(user) != len(banned):
            return False
        for i, v in enumerate(user):
            if banned[i] != v and banned[i] != '*':
                    return False
        return True

    
    s = set()
    
    visited = [False] * u_len
    
    def dfs(depth, curr):
        if depth == b_len:
            s.add(tuple(sorted(curr)))
            return
        
        for i in range(u_len):
            if visited[i]:
                continue
            if is_valid(user_id[i], banned_id[depth]):
                visited[i] = True
                curr.append(user_id[i])
                dfs(depth + 1, curr)
                curr.pop()
                visited[i] = False
                
    dfs(0, [])
    return len(s)
