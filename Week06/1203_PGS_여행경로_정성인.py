def solution(tickets):
    answer, visited = [], []
    tickets = sorted(tickets)
    
    next = 'ICN'
    answer.append(next)
    
    while tickets:
        temp = []
        for i in range(len(tickets)-1, -1, -1):
            if tickets[i][0] == next and tickets[i] not in visited and (not temp or tickets[i][1] < temp[0]):
                temp = [tickets[i][1], i]

        if not temp:
            tickets.append([answer[-2], next])
            visited.append([answer[-2], next])
            next = answer[-2]
            answer.pop(len(answer) - 1)
            continue
        
        next = temp[0]
        answer.append(next)
        tickets.pop(temp[1])
        visited = []
        
    return answer