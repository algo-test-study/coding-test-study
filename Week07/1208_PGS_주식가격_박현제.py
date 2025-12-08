"""
가격 떨어지지 않은 기간(초)

알고리즘
- 스택: 아직 가격이 안떨어진 인덱스
- curr < top -> 바로 계산
- 안 떨어짐 -> 거리

시간복쟙도
- O(N)
"""
def solution(prices):
    n = len(prices)
    ans = [0] * n
    stk = []
    for i in range(n):
        while stk and prices[i] < prices[stk[-1]]:
            top = stk.pop()
            ans[top] = i -top

        stk.append(i)

    while stk:
        top = stk.pop()
        ans[top] = n-top - 1

    return ans
