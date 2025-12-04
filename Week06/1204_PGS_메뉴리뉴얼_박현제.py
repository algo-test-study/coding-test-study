"""
답참고

알고리즘
- 정렬
- 주문 조합 만듦
- 최빈 횟수 구하기
시간복잡도
- O(nCk)

"""

from collections import defaultdict
def solution(orders, course):
    ans = []

    def comb(order, start, path, length, combs):
      
        if len(path) == length:
            combs[path] += 1
            return
        for i in range(start, len(order)):
            comb(order, i + 1, path + order[i], length, combs)

    for course_len in course:
        combs = defaultdict(int)
        cnt = 0

        for order in orders:
            s_order = ''.join(sorted(order))
            comb(s_order, 0, "", course_len, combs)

        if combs:
            cnt = max(combs.values())

        if cnt >= 2:
            for menu, c in combs.items():
                if c == cnt:
                    ans.append(menu)

    return sorted(ans)
