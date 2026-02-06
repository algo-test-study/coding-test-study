"""
- 양쪽으로 전파 전달 -> 증설 cnt
알고리즘
- 왼쪽부터 차례로 
- 전파 시작점 구하기
- 설치

시간복잡도
- O(N)
"""
def solution(n, stations, w):
    answer = 0
    area = 2 * w + 1
    
    curr = 1
    
    for station in stations:
        s = station - w
        
        if curr < s:
            dist = s - curr
            
            cnt = dist // area
            if dist % area > 0:
                cnt += 1
            answer += cnt
        curr = station + w + 1
    
    if curr <= n:
        dist = n - curr + 1
        cnt = dist // area
        if dist % area > 0:
            cnt += 1
        answer += cnt
            
    return answer
