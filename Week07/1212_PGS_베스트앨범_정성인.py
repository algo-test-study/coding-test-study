from collections import defaultdict

def solution(genres, plays):
    answer = []
    genres_hash = {}
    best_plays = defaultdict(list)
    
    for i, genre in enumerate(genres):
        genres_hash[genre] = genres_hash.get(genre, 0) + plays[i]
        best_plays[genre].append((i, plays[i]))

    best_genres = [i[0] for i in sorted(genres_hash.items(), key=lambda x: -x[1])]

    for bg in best_genres:
        songs = best_plays[bg]
        
        answer.extend([i[0] for i in sorted(songs, key=lambda x:(-x[1], x[0]))[:2]])
        
    return answer
