"""
O(nlog(max(G + Y + R)))
"""
from math import gcd
def solution(signals):
    lights = []
    for g, y, r in signals:
        lights.append((g + y + r, g, g + y)) # 주기, y시작, y끝

    total = lights[0][0]
    
    for period, _, _ in lights[1:]:
        total = total * period // gcd(total, period) # LCM

    for x in range(total):
        is_all = True
        for period, start, end in lights:
            if not (start <= (x % period) < end):
                is_all = False
                break
                
        if is_all:
            return x+1

    return -1
