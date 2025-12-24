"""
n편 중 논문 인용 수 >= h 인 논문 h개. 나머지 논문 인용 수 <= h -> h의 최댓값 (h-index)

알고리즘
- 내림차순 정렬
-> [6,5,3,1,0]
- 현재 인용수 >= curr 인덱스+1

시간복잡도
- O(len(citations))
"""
def solution(citations):

    citations.sort(reverse=True)
    h_idx = 0
    for v in citations:
        if v > h_idx:
            h_idx += 1
    return h_idx
