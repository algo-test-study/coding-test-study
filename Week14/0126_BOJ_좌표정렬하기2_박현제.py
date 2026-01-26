"""
알고리즘
-정렬
시간복잡도
-O(nlgon)
"""
n = int(input())

lst = [list(map(int, input().split())) for _ in range(n)]

lst.sort(key=lambda x: (x[1], x[0]))

for x, y in lst:
	print(f"{x} {y}")
