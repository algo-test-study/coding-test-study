"""
알고리즘
- 정렬
- 가운데만 슬라이싱
- 평균

시간복잡도
- O(n)

"""
import sys
input = sys.stdin.readline

n = int(input())
if n == 0:
    print(0)
    sys.exit()
    
lst = [int(input()) for _ in range(n)]

lst.sort()

k = int(n * 0.15 + 0.5)

lst = lst[k:n - k]

print(int(sum(lst) / len(lst) + 0.5))
