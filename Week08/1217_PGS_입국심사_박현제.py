
"""
n명 통과하는데 걸리는 시간 최솟값

알고리즘
- 시간 -> 0~사람수*시간최댓값 중 존재 
- 특정 시간 주어질 때 모든 사람이 심사받을 수 있는지 보기
- FFFFFTTTTT... -> 이분탐색
- 심사 받을 수 있는지 판단
[7 10] 28 시간 -> 몇명을 쓸 수 있나?
28 // 7 -> 4
28 // 10 -> 2 
n <= 4+2 -> true

시간복잡도
- O(log(10^18)*10^5)
"""
def solution(n, times):
    answer = -1
    s = 1
    e = n*max(times)
    
    while s <= e:
        mid = (s + e) // 2
        
        def is_valid():
            people = 0
            for t in times:
                people += mid // t
            if n <= people: 
                return True
            return False

        if is_valid():
            e = mid - 1
            answer = mid
        else:      
            s = mid + 1
    
    return answer
