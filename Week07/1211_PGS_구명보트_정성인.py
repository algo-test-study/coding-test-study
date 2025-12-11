def solution(people, limit):
    answer = 0
    people.sort()
    
    i = len(people)-1
    while i > 1:
        if people[i] + people[0] > limit: 
            i -= 1
            continue
        for j in range(i-1, -1, -1):
            if people[i] + people[j] <= limit:
                people.pop(i)
                people.pop(j)
                answer += 1
                break
        i -= 1
        
    return answer + 1 if people else answer