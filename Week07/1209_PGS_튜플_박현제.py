"""
알고리즘
- s 요소 길이 순서로 오름차순 정렬
- set에 이미 있는 요소가 아니면 답에 add
- 현재 값 set에 add
시간복잡도
O(s.length)
- 
"""

def solution(s):
    answer = []
    
    s = s[2:-2]
    s = s.split("},{")
    
    visited = set()
        
    lst = [list(map(int, v.split(","))) for v in s]

    lst.sort(key=len)
    
    for v in lst:
        for curr in v:
            if curr not in visited:
                answer.append(curr)
                visited.add(curr)
                break
        
    return answer
