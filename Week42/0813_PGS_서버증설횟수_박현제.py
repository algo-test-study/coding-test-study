"""
O(1)
"""
def solution(players, m, k):

    answer = 0
    expire = [0] * 24
    curr = 0

    for t in range(24):
        curr -= expire[t] 
        cnt = players[t] // m
        if cnt > curr:
            server = cnt - curr
            answer += server
            curr += server
            
            if t + k < 24:
                expire[t + k] += server

    return answer
