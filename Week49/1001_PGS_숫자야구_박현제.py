from itertools import permutations
def solution(n, submit):
    people = list(permutations(range(1, 10), 4))

    def get_result(query, answer):
        strike = 0
        ball = 0

        for i in range(4):
            if query[i] == answer[i]:
                strike += 1
            elif query[i] in answer:
                ball += 1

        return strike, ball

    def to_number(numbers):
        return (
            numbers[0] * 1000
            + numbers[1] * 100
            + numbers[2] * 10
            + numbers[3]
        )

    def find_best_query(people):
        best_query = people[0]
        best_cnt = len(people) + 1

        for query in people:
            groups = {}

            for answer in people:
                result = get_result(query, answer)

                if result not in groups:
                    groups[result] = 0

                groups[result] += 1

                if groups[result] >= best_cnt:
                    break

            else:
                max_cnt = max(groups.values())

                if max_cnt < best_cnt:
                    best_cnt = max_cnt
                    best_query = query

        return best_query

    query = (1, 2, 3, 4)

    for _ in range(n):
        number = to_number(query)
        result = submit(number)

        if result == "4S 0B":
            return number

        strike = int(result[0])
        ball = int(result[3])

        next_people = []

        for answer in people:
            if get_result(query, answer) == (strike, ball):
                next_people.append(answer)

        people = next_people

        if len(people) == 1:
            return to_number(people[0])

        query = find_best_query(people)

    return to_number(people[0])
