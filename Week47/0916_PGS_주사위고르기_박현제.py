from itertools import combinations
from bisect import bisect_left


def solution(dice):
    n = len(dice)
    best, max_wins = None, -1
    def get_sums(dice, idxs):
        sums = [0]
        for d in idxs:
            new_sums = []
            for s in sums:
                for v in dice[d]:
                    new_sums.append(s + v)
                    sums = new_sums
        return sums

    for a in combinations(range(n), n // 2):
        b = [i for i in range(n) if i not in a]

        a_sums = get_sums(dice, a)
        b_sums = sorted(get_sums(dice, b))

        wins = 0
        for sa in a_sums:
            wins += bisect_left(b_sums, sa)

        if wins > max_wins:
            max_wins, best = wins, a
    ans = [i + 1 for i in best]
    return ans
