"""
모든 사람 구출에 필요한 구명보트 최소 개수. 한 보트 당 최대 2명 태움 가능
알고리즘
- 리스트 오름차순 정렬
- 몸무게 가장 무거운 사람 + 가벼운 사람 조합으로 태움
시간복잡도
- O(len(people))
"""
def solution(people, limit):    
    people.sort()
    
    answer = 0
    s = 0
    e = len(people) - 1
    
    while s <= e:
        if limit >= people[s] + people[e]:
            s += 1
            e -= 1
        elif limit >= people[e]:
            e -= 1
        answer += 1

    return answer
