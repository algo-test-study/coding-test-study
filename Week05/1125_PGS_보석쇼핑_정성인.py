def solution(gems):
    kinds = len(set(gems))
    cur = dict()
    
    left = 0
    answer = [0, len(gems)-1]
    
    for right in range(len(gems)):
        cur[gems[right]] = cur.get(gems[right], 0) + 1
        
        if len(cur) != kinds:
            continue
        
        while len(cur) == kinds:
            if answer[1]-answer[0] > right-left:
                answer = [left, right]
            
            cur[gems[left]] -= 1
            if cur[gems[left]] == 0:
                del cur[gems[left]]
            left += 1
        
    return [answer[0]+1, answer[1]+1]
