# 구현, O(len(privacies))

def solution(today, terms, privacies):
    answer = []
    
    term = {}
    
    c_year, c_month, c_day = map(int, today.split("."))
    c_time = c_year*12*28 + c_month*28 + c_day
    
    for t in terms:
        kind, month = t.split()
        term[kind] = int(month)*28
    
    
    for i, p in enumerate(privacies):
        curr, kind = p.split(" ")
        year, month, day = map(int, curr.split("."))
        time = year*12*28 + month*28 + day

        if time + term[kind] <= c_time:
            answer.append(i+1)
            
    return answer
