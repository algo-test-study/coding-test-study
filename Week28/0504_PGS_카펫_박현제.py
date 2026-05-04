def solution(brown, yellow):

    total = brown + yellow
    
    n = int(total ** 0.5)
    
    
    for h in range(1, n+1):
        if total % h == 0:
            w = total//h
            if (w-2) * (h-2) == yellow:
                ans = [w, h]
                break
    
    return ans
