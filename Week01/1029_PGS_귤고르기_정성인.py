def solution(k, tangerine):
    answer = 0
    D = dict()
    for t in tangerine:
        D[t] = D.get(t, 0) + 1
    
    sorted_tangerine = sorted(D.values(), reverse=True)
    
    for cnt in sorted_tangerine:
        if k > 0:
            answer += 1
            k -= cnt
        else:
            break
    return answer
