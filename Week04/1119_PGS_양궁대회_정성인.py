answer = [-1, [-1]]

def get_score_diff(apeach, ryan):
    score_apeach, score_ryan = 0, 0
    
    for i in range(len(apeach)):
        if apeach[i] == 0 and ryan[i] == 0:
            continue
        elif apeach[i] < ryan[i]:
            score_ryan += 10-i
        else:
            score_apeach += 10-i
    if score_ryan > score_apeach:
        return score_ryan-score_apeach
    else:
        return -1

def dfs(i, info, left, ryan):
    global answer
    
    if i == len(info):
        if left != 0:
            return
        diff = get_score_diff(info, ryan)

        if diff != -1:
            if answer[0] < diff:
                answer = [diff, ryan]
            elif answer[0] == diff:
                for i in range(len(info)-1, -1, -1):
                    if answer[1][i] < ryan[i]:
                        answer[1] = ryan
                        break
                    elif answer[1][i] > ryan[i]:
                        break
        return
    
    if left < 0:
        return
    
    for score in range(left, -1, -1):
        dfs(i+1, info, left-score, ryan+[score])

def solution(n, info):
    dfs(0, info, n, [])
    
    return answer[1] if answer[0] > 0 else [-1]
