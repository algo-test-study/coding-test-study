"""
- 공유기: c
- 집: n
- 가장 인접한 두 공유기 사이 거리 최대
알고리즘
- 완탐 -> nCc*n -> 너무 큼
- 최대 거리 = mid -> 이분 탐색
- TTTTTFFF
- 0 ~ (마지막 집 - 처음 집)
- is_valid -> mid 최대 거리 기준으로 모든 공유기를 다 쓸 수 있으면 true / 아니면 false

시간복잡도
- 최대 집 거리 = m
- O(logm * n)
"""
n, c = map(int, input().split())

lst = [int(input()) for i in range(n)]

lst.sort()

s = 1
e = lst[n - 1] - lst[0]

while s <= e:
	mid = (s + e)//2

	def is_valid(mid, c):
		s = 0
		e = 1
		c -= 1
		while True:
			if e == n : break
			if lst[e] - lst[s] >= mid: # 인접 공유기 사이 거리 적합 -> 선택 가능
				s = e 
				c -= 1
			e += 1
			if c == 0: return True
		return c <= 0


	if is_valid(mid, c):
		s = mid + 1
	else:
		e = mid - 1

print(e)
