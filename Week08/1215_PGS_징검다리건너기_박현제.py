"""
징검다리 조건
- 0이면 밟을 수 없다
- 무조건 가까운 디딤돌로 뛰어야함
- 건너는 디딤돌 수 한계: k

알고리즘
- 최대 명수 바꿔가면서 서칭 TTTTFFFF -> 마지막 T
- 이분 탐색

시간복잡도
- O(len(stone)*log(max(stone)))
"""
def solution(stones, k):

    def is_valid(mid):
        cnt = 0
        for stone in stones:
            if stone - (mid-1) <= 0:
                cnt+=1
            else:
                cnt = 0
                
            if cnt >= k:
                return False
        return True
            
    answer = 0  
    s = 1
    e = max(stones)
    
    while s <= e:
        mid = (s + e) // 2
        
        if is_valid(mid):
            answer = mid
            # answer = max(answer, mid)
            s = mid + 1
        else:
            e = mid - 1
    
    return answer
