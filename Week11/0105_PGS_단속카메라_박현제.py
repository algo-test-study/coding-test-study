"""
알고리즘
- 끝점 기준 정렬
-20, -15 / -18, -13 / -14, -5 / -5, -3
-> -15 >= -18, -15 < -14 -> 안됨
-> -5 >= -5 -> 가능
-> if 끝(기준) >= 다음 끝 -> pass else ans++, 기준 = 다음 끝

시간복잡도
- O(n)
"""
def solution(routes):
    answer = 1
    
    routes.sort(key=lambda x : x[1])
    
    n = len(routes)
    
    prev_end = routes[0][1]
    for i in range(1, n):
        next_start = routes[i][0]
        next_end = routes[i][1]
        if prev_end < next_start:
            answer += 1
            prev_end = next_end
    return answer
