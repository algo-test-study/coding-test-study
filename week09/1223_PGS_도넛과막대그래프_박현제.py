"""
답 참고

알고리즘
- 도넛: in 1, out 1
- 막대: 시작 0,1 / 중간 1,1 / 1,0
- 8자: 1개만 2,2 나머지 1,1
- 생성: 0,그래프수 -> out중 in 0 찾기

시간복잡도
- O(len(edges))
"""
from collections import defaultdict
def solution(edges):
    answer = [0,0,0,0]
    in_d = defaultdict(int)
    out_d = defaultdict(int)
    
    for s,e in edges:
        in_d[e] += 1
        out_d[s] += 1
        
    for curr in out_d:
        if out_d[curr] >= 2:
            if curr not in in_d:
                answer[0] = curr #생성 노드
            else:
                answer[3] += 1 #8자 중심
                
    for curr in in_d: #막대
        if curr not in out_d:
            answer[2] += 1
    answer[1] = out_d[answer[0]] - answer[2] - answer[3]
    return answer
