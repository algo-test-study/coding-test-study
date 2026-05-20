function solution(genres, plays) {
  const genreTotal = {};
  const songs = {};

  for (let i = 0; i < genres.length; i++) {
    const genre = genres[i];
    const play = plays[i];

    genreTotal[genre] = (genreTotal[genre] || 0) + play;

    if (!songs[genre]) songs[genre] = [];
    songs[genre].push([i, play]);
  }

  const sortedGenres = Object.keys(genreTotal).sort(
    (a, b) => genreTotal[b] - genreTotal[a]
  );

  const answer = [];

  for (const genre of sortedGenres) {
    songs[genre].sort((a, b) => {
      if (b[1] !== a[1]) return b[1] - a[1];
      return a[0] - b[0];
    });

    answer.push(songs[genre][0][0]);

    if (songs[genre].length > 1) {
      answer.push(songs[genre][1][0]);
    }
  }

  return answer;
}