def solution(coin, cards):
    n = len(cards)
    target = n + 1

    mine = set(cards[:n//3])
    temp = set()
    pair = 0

    for v in mine:
        if target - v in mine:
            pair += 1
    pair //= 2

    cnt = 1
    
    for i in range(n//3, n, 2):
        a = cards[i]
        b = cards[i + 1]

        if coin > 0 and target - a in mine:
            coin -= 1
            pair += 1
        else:
            temp.add(a)

        if coin > 0 and target - b in mine:
            coin -= 1
            pair += 1
        else:
            temp.add(b)
            
        if pair <= 0 and coin >= 2:
            for v in list(temp):
                other = target - v

                if other in temp:
                    pair += 1
                    coin -= 2

                    temp.remove(v)
                    temp.remove(other)
                    break
                    
        if pair > 0:
            pair -= 1
            cnt += 1
        else:
            break

    return cnt
