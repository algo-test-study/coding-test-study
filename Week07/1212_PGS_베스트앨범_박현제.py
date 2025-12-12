"""
답참고함

알고리즘
- 장르별 재생 횟수
- 장르 우선순위 정렬
- 장르별 노래 순위 정렬
시간복잡도
- O(nlogn)
"""
from collections import defaultdict
def solution(genres, plays):
    totals = defaultdict(int)
    songs = defaultdict(list) 

    for i in range(len(genres)):
        genre = genres[i]
        play = plays[i]
        totals[genre] += play
        songs[genre].append((i, play))
    s_genres = sorted(
        totals.items(), 
        key=lambda x: x[1], 
        reverse=True
    )
    
    answer = []
    for genre_name, total_play in s_genres: 
        s_songs = sorted(
            songs[genre_name], 
            key=lambda x: (-x[1], x[0])
        )
        for i in range(min(len(s_songs), 2)):
            answer.append(s_songs[i][0])
            
    return answer
