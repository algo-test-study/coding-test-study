def solution(n, k, cmd):
    deleted = []
    prev = [i-1 for i in range(n)]
    nxt = [i+1 for i in range(n)]
    nxt[-1] = -1
    
    for c in cmd:       
        if c[0] == 'U':
            for _ in range(int(c.split()[1])):
                k = prev[k]
        elif c[0] == 'D':
            for _ in range(int(c.split()[1])):
                k = nxt[k]
        elif c[0] == 'C':
            deleted.append(k)
            
            if prev[k] != -1:
                nxt[prev[k]] = nxt[k]
            if nxt[k] != -1:
                prev[nxt[k]] = prev[k]
            
            if nxt[k] != -1:
                k = nxt[k]
            else:
                k = prev[k]
        else:
            last_deleted = deleted.pop()
            if prev[last_deleted] != -1:
                nxt[prev[last_deleted]] = last_deleted
            if nxt[last_deleted] != -1:
                prev[nxt[last_deleted]] = last_deleted
    answer = ['O']*n
    for i in deleted:
        answer[i] = 'X'
    return ''.join(answer)
