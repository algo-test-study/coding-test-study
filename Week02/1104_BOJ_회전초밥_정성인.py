import sys
input = sys.stdin.readline

N, d, k, c = map(int, input().split()) # 접시 수, 가짓수, 연속, 쿠폰
sushi = [int(input()) for _ in range(N)]

eaten = [0] * (d+1)
eaten[c] = 1
cnt = 1

for i in range(k):
    if eaten[sushi[i]] == 0:
        cnt += 1
    eaten[sushi[i]] += 1
answer = cnt

for i in range(N):
    last, cur = sushi[i], sushi[(i+k) % N]
    
    eaten[last] -= 1
    if eaten[last] == 0:
        cnt -= 1
    
    if eaten[cur] == 0:
        cnt += 1
    eaten[cur] += 1

    answer = max(answer, cnt)

print(answer)
