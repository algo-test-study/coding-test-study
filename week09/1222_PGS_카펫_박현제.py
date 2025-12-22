"""
알고리즘
- 전체 w*h (w >= h)
- 갈색 = w*h - (w-2*h-2)
- 노란색 = (w-2*h-2)
- 전체 탐색

시간복잡도
O((brown + yellow)^(1/2))
"""
def solution(brown, yellow):
    answer = []
    
    total = brown + yellow
    
    for h in range(3, total + 1):
        if total**0.5 < h: break; 
        
        if total % h == 0:
            w = total // h
            
            if (w - 2)*(h - 2) == yellow:
                answer = [w, h]
                break;
        
    return answer
