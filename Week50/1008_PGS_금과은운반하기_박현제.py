def solution(a, b, g, s, w, t):
    start = 0
    end = 4*10**14
    
    while start <= end:
        mid = (start + end) // 2

        gold = 0
        silver = 0
        total = 0

        for i in range(len(g)):
            cnt = mid // (2 * t[i])

            if mid % (2 * t[i]) >= t[i]:
                cnt += 1

            cap = cnt * w[i]

            gold += min(g[i], cap)
            silver += min(s[i], cap)
            total += min(g[i] + s[i], cap)

        if gold >= a and silver >= b and total >= a + b:
            end = mid - 1
        else:
            start = mid + 1

    return start
