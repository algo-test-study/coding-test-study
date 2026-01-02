"""
알고리즘
- dfs
- + / - 분기
- depth >= n 이면 멈춤 target == 결과값 -> ans++
시간복잡도
- O(2^20)
"""
def solution(numbers, target):
    answer = 0

    n = len(numbers)

    def dfs(depth, total):
        nonlocal answer

        if depth == n:
            if total == target:
                answer += 1
            return
        
        dfs(depth + 1, total + numbers[depth])
        dfs(depth + 1, total - numbers[depth])
    dfs(0, 0)

    return answer