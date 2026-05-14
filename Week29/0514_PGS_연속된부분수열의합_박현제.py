def solution(sequence, k):
    
    s = 0
    e = 0
    total = sequence[e]
    n = len(sequence)
    min_len = float('inf')

    while s <= e and e < n:
        if total > k:
            total -= sequence[s]
            s+=1
        elif total < k:
            e+=1
            if e >= n:
                continue
            total += sequence[e]
        else:
            if min_len > e - s:    
                min_len = e - s
                answer = [s, e]
            total -= sequence[s]
            s+=1
            e+=1
            if e >= n:
                continue
            total += sequence[e]
    return answer
