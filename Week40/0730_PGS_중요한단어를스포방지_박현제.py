"""
set 2개
O(n)
"""
def solution(message, spoiler_ranges):
    n = len(message)
    is_spoiled = [False] * n
    for start, end in spoiler_ranges:
        for i in range(start, end + 1):
            is_spoiled[i] = True

    spoiled_words, normal_words = set(), set()
    word_start = 0
    has_spoiled = False

    for i in range(n + 1):
        if i == n or message[i] == ' ':
            if i > word_start:
                word = message[word_start:i]
                if has_spoiled:
                    spoiled_words.add(word)
                else:
                    normal_words.add(word)
            word_start = i + 1
            has_spoiled = False
        elif is_spoiled[i]:
            has_spoiled = True

    return len(spoiled_words - normal_words)
