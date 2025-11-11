function solution(places) {
  const N = 5;
  const dr = [1, -1, 0, 0];
  const dc = [0, 0, 1, -1];

  const inRange = (r, c) => r >= 0 && r < N && c >= 0 && c < N;

  const ok = (room) => {
    const board = room.map((row) => row.split(''));

    for (let sr = 0; sr < N; sr++) {
      for (let sc = 0; sc < N; sc++) {
        if (board[sr][sc] !== 'P') continue;

        const q = [[sr, sc, 0]];
        const visited = Array.from({ length: N }, () => Array(N).fill(false));
        visited[sr][sc] = true;

        while (q.length) {
          const [r, c, d] = q.shift();

          for (let k = 0; k < 4; k++) {
            const nr = r + dr[k];
            const nc = c + dc[k];
            const nd = d + 1;

            if (!inRange(nr, nc) || visited[nr][nc] || nd > 2) continue;
            if (board[nr][nc] === 'X') continue;

            if (board[nr][nc] === 'P') return false;

            visited[nr][nc] = true;
            q.push([nr, nc, nd]);
          }
        }
      }
    }
    return true;
  };

  return places.map((room) => (ok(room) ? 1 : 0));
}
