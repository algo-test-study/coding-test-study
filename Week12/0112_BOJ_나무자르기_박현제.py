"""
알고리즘
- 나무 합 >= m, h 최댓값
- 절단기 높이 h <= 10^9 -> 최적화: 나무 max값
- TTTTFFFF -> 파라메트릭 서치
시간복잡도
- O(log(max(lst)))
"""
n, m = map(int, input().split())

lst = list(map(int, input().split()))

s = 0
e = max(lst)

while s <= e:
	mid = (s + e)//2

	def is_valid():
		total = 0
		for curr in lst:
			if curr - mid >= 0:
				total += curr - mid
		if total >= m:
			return True
		return False

	if is_valid():
		s = mid + 1
	else:
		e = mid - 1

print(e)
