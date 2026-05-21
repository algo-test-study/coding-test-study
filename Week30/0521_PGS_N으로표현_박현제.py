# dp
def solution(N, number):
    dp = [set() for _ in range(9)]

    for i in range(1, 9):
        s = set()

        s.add(int(str(N) * i))

        for j in range(1, i):
            for x in dp[i - j]:
                for y in dp[j]:
                    s.add(x + y)
                    s.add(x - y)
                    s.add(x * y)

                    if y != 0:
                        s.add(x // y)

                        
        if number in s:
            return i

        dp[i] = s

    return -1
