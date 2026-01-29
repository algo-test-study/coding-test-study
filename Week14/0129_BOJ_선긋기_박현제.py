"""
알고리즘
- 정렬
- now s <= prev e -> 한줄로 만들기. 현재값 갱신
- 그게 아니면
	(prev e - s) ans 에 추가
	현재 값 갱신

시간복잡도
- O(n)

"""
import sys
input = sys.stdin.readline

n = int(input())
lst = [list(map(int, input().split())) for _ in range(n)]

lst.sort()

ans = 0
std_s, std_e = lst[0]

for i in range(1, n):
    now_s, now_e = lst[i]
    if now_s <= std_e:
        std_e = max(std_e, now_e)
    else:
        ans += std_e - std_s
        std_s, std_e = now_s, now_e

ans += std_e - std_s

print(ans)
