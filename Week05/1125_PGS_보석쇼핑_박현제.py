"""
알고리즘
- 투포인터
- 처음에 모든 보석 종류수 찾기 -> set
- end/start 인덱스 늘려가며 딕셔너리에 보석 추가/삭제
- s++ -> 종류--
- e++ -> 종류++
- 보석 종류 수 == dict key 개수 -> 가능한 구간
시간복잡도
- O(gems.length)
"""
def solution(gems):
    n = len(set(gems))
    d = {}
    s, e = 0, 0
    ans = [1, len(gems)]
    min = len(gems)
    d[gems[0]] = 1
  
    while s <= e and e < len(gems):       
        if len(d) == n:
            if min > e - s:
                min = e - s
                ans[0] = s + 1
                ans[1] = e + 1
        
            d[gems[s]] -= 1
            if d[gems[s]] == 0:
                del d[gems[s]]
            s+=1
            
        else:
            if e == len(gems)-1:
                break
            e+=1
            d[gems[e]] = d.get(gems[e], 0) + 1
          
    return ans
