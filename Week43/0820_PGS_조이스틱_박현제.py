"""
O(n)

"""
def solution(name):
    answer = 0
    
    n = len(name)
    
    for c in name:
        answer += min(ord(c) - ord('A'), ord('Z') - ord(c) + 1)
    
    min_cnt = n - 1
    s, e = 1, 1
    while s < n:
        if name[s] == 'A':
            e = s
            while e < n:
                if name[e] != 'A':
                    break
                e += 1
        
            move_rl = (s - 1)* 2 + (n - e)
            move_lr = (n - e) * 2 + s - 1
            min_cnt = min(min_cnt, move_rl, move_lr)
            s = e
            
        else:
            s += 1

    answer += min_cnt
   
    return answer
