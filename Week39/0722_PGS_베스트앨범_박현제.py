"""
고유 번호
1. 노래 개수 많은 장르 -> dict
2. 재생 횟수 높은 -> play
3. 고유 번호 낮은 -> id 

O(nlogn)
"""
from collections import defaultdict
def solution(genres, plays):
    answer = []
    n = len(genres)
    
    total = defaultdict(int)
    genre_group = defaultdict(list)
    
    for i in range(n):
        genre, play = genres[i], plays[i]
        total[genre] += play
        genre_group[genre].append((i, play))
        
    for genre in sorted(total, key=lambda g: -total[g]):
        for i, _ in sorted(genre_group[genre], key=lambda x: (-x[1], x[0]))[:2]: #2개  제한
            answer.append(i)
    return answer
