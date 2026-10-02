def solution(n, bans):

    def to_str(num):
        ans = []
        while num > 0:
            num -= 1
            ans.append(chr(ord('a') + num % 26))
            num //= 26
        return ''.join(reversed(ans))
    
    def to_num(s):
        num = 0
        for c in s:
            num = num * 26 + (ord(c) - ord('a') + 1)
        return num
    
    ban_nums = sorted(to_num(b) for b in bans)

    for b in ban_nums:
        if b <= n:
            n += 1
        else:
            break

    return to_str(n)
