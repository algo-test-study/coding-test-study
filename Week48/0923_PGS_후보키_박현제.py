from itertools import combinations
def solution(relation):
    keys = []

    for size in range(1, len(relation[0]) + 1):
        for cols in combinations(range(len(relation[0])), size):

            min_v = True

            for key in keys:
                if set(key) <= set(cols):
                    min_v = False
                    break

            if not min_v:
                continue

            unique = set()

            for row in relation:
                data = tuple(row[col] for col in cols)
                unique.add(data)

            if len(unique) == len(relation):
                keys.append(cols)

    return len(keys)
