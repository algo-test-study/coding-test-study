def solution(prices):
    answer = [0] * len(prices)
    n = len(prices)

    stack = []
    for i in range(n):
        while stack and prices[i] < prices[stack[-1]]:
            j = stack.pop()
            answer[j] = i - j
        stack.append(i)

    for idx in stack:
        answer[idx] = n - idx - 1

    return answer


print(solution([1, 2, 3, 2, 3]))
