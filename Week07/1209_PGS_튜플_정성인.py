def solution(s):
    answer = []
    _list = sorted(list(s[2:-2].split('},{')), key=lambda x:len(x))

    for tup in _list:
        chunk = list(map(int, tup.split(',')))
        answer.extend(set(chunk)-set(answer))
    return answer
