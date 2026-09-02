def solution(numbers):

    def is_valid(binary, start, end, parent):
        if start > end:
            return True

        mid = (start + end) // 2
        curr = binary[mid]

        if parent == '0' and curr == '1':
            return False

        return is_valid(binary, start, mid - 1, curr) and is_valid(binary, mid + 1, end, curr)

    ans = []

    for v in numbers:
        binary = bin(v)[2:]

        size = 1
        while size - 1 < len(binary):
            size *= 2

        binary = '0' * (size - 1 - len(binary)) + binary

        if is_valid(binary, 0, len(binary) - 1, '1'):
            ans.append(1)
        else:
            ans.append(0)

    return ans
