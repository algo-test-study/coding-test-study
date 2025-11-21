def solution(numbers):
    if len(numbers) == numbers.count(0):
        return '0'
    numbers.sort(key=lambda x:int((str(x)*4)[:4]), reverse=True)
    return ''.join(map(str, numbers))
