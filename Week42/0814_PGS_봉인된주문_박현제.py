"""
O(blogb)
"""
from bisect import bisect_right
def solution(n, bans):
    cum = [0] * 13
    for l in range(1, 13):
        cum[l] = cum[l- 1] + (26 ** l)

    ban_idx = []
    
    for b in bans:
        r = 0
        for ch in b:
            r = r * 26 + (ord(ch) - 97)
        ban_idx.append(cum[len(b) - 1] + r + 1)
    ban_idx.sort()

    ans = 0
    s, e = 1, n + len(bans)
    
    while s <= e:
        mid = (s + e) // 2
        if mid - bisect_right(ban_idx, mid) >= n:
            ans = mid
            e = mid - 1
        else:
            s = mid + 1

    l = 1
    
    while cum[l] < ans:
        l += 1
    r = ans - cum[l - 1] - 1
    ans = []
    
    for _ in range(l):
        ans.append(chr(97 + r % 26))
        r //= 26

    return ''.join(reversed(ans))
