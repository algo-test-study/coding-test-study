
"""
알고리즘
- 모든 사람 심사 최소 시간
- 1. 완탐 - O(심사관^사람수) -> 불가능
- 2. 파라메트릭 서치
    - TTTTFFFFF 구조. 대상: 시간 1-최대심사시간*n
    - is_valid: 타겟 시간일 떄 모든 사람 심사 가능한지 체크
        심사 인원 += 시간 // 현재 심사대 값 반복 
        심사 인원 >= n -> 통과
시간복잡도

"""
def solution(n, times):
    s = 1
    e = n*max(times)
    
    while s <= e:
        mid = (s + e)//2
        
        def is_valid():
            total = 0
            
            for t in times:
                total += mid//t
            if total >= n:
                return True
            return False
            
        if is_valid():
            e = mid - 1
        else:
            s = mid + 1
    return s
