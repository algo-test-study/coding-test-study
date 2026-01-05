
"""
알고리즘
- 몸무게 큰사람 우선으로 소비 -> 내림차순 정렬
- 최대한 몸무게 큰사람 + 작은사람 사용

시간복잡도
- O(50000)
"""
def solution(people, limit):
    answer = 0
    
    n = len(people)
    
    people.sort(reverse=True)
    
    s = 0
    e = n - 1
  
    while s <= e:
        # if s == e: # 마지막 사람이기 떄문에 total <= limit 이든 말든 상관없다. 
        #     answer += 1
        #     break
        total = people[s] + people[e] 
        if total <= limit:
            s += 1
            e -= 1
        else:
            s += 1
        answer += 1

    return answer
