# dfs, O(2^n)
def solution(numbers, target):
    answer = 0
    def dfs(depth, curr):
        nonlocal numbers, target, answer
        
        if depth == len(numbers):
            if curr == target:
                answer+=1  
            return
        
        dfs(depth + 1, curr + numbers[depth])
        dfs(depth + 1, curr - numbers[depth])
    dfs(0, 0)
    
    return answer
