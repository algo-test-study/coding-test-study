import math

def solution(progresses, speeds):
    answer = []
    remain_days = [math.ceil((100-p)/s) for p, s in zip(progresses, speeds)]
    stack = []
    
    for d in remain_days:
        if not stack:
            stack.append(d)
        elif d <= stack[0]:
            stack.append(d)
        else:
            answer.append(len(stack))
            stack = [d]
    answer.append(len(stack))
    
    return answer
