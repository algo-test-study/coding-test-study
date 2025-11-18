N, K = map(int, input().split())
L = list([i for i in range(1, N+1)])
answer = []
idx = 0

while L:
    idx = (idx+K-1) % len(L)
    answer.append(L.pop(idx))

print('<', end = '')
print(', '.join(map(str, answer)), end = '')
print('>')
