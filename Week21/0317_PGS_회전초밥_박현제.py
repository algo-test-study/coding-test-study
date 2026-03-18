
"""
슬라이딩 윈도우, O(n)
"""
import sys
input = sys.stdin.readline


# sys.stdin = open('input.txt', 'r')  # 삭제


n, d, k, c = map(int, input().split())
arr = [int(input()) for _ in range(n)]

cnt = {}

for i in range(k):
    cnt[arr[i]] = cnt.get(arr[i], 0) + 1

max_kind = len(cnt)
if c not in cnt:
    max_kind += 1

e = k - 1

for s in range(n):
    start = arr[s]

    if cnt[start] == 1:
        del cnt[start]
    else:
        cnt[start] -= 1

    e = (e + 1) % n
    end = arr[e]
    cnt[end] = cnt.get(end, 0) + 1

    curr = len(cnt)
    if c not in cnt:
        curr += 1

    max_kind = max(max_kind, curr)

print(max_kind)
