"""
알고리즘
- 종류별로 몇갠지 세기
- 입기 + 안입기1
시간복잡도
- O(len(clothes))
"""
from collections import defaultdict
def solution(clothes):
    
    cnt = defaultdict(int)

    for name, kind in clothes:
        cnt[kind] += 1

    ans = 1
    for kind in cnt:
        ans *= cnt[kind] + 1
    return ans - 1
