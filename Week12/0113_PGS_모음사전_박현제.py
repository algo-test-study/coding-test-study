"""
알고리즘
- 가중치 설정
- 단어 계산
시간복잡도
- O(len(word))
"""
def solution(word):
    alphabet = "AEIOU"
    weight = [781, 156, 31, 6, 1] 
    
    total = 0
    
    for i in range(len(word)):
        index = alphabet.index(word[i])
        total += index * weight[i] + 1
    
    return total
