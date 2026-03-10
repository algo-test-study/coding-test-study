"""
알고리즘
- 연결 리스트, 스택

시간복잡도
- O(n + m + x)

"""
def solution(n, k, cmd):
    answer = []
    
    removed = [False] * n # 처음부터 'O'로 해도됨
    prev = [i-1 for i in range(n)]
    nxt = [i+1 for i in range(n)]
    nxt[n-1] = -1

    now = k
    stack = []

    for command in cmd:
        if command == "C":
            stack.append(now)
            removed[now] = True

            if prev[now] != -1:
                nxt[prev[now]] = nxt[now]

            if nxt[now] != -1:
                prev[nxt[now]] = prev[now]

            if nxt[now] != -1:
                now = nxt[now]
            else:
                now = prev[now]

        elif command == "Z":
            restore = stack.pop()
            removed[restore] = False

            if prev[restore] != -1:
                nxt[prev[restore]] = restore

            if nxt[restore] != -1:
                prev[nxt[restore]] = restore

        else:
            op, x = command.split()
            x = int(x)

            if op == "U":
                for _ in range(x):
                    now = prev[now]
            else:
                for _ in range(x):
                    now = nxt[now]

    for i in range(n):
        if removed[i]:
            answer.append("X")
        else:
            answer.append("O")

    return "".join(answer)
