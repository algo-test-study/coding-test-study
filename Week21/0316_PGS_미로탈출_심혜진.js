function solution(maps) {
  const numRows = maps.length;
  const numCols = maps[0].length;

  const dr = [-1, 1, 0, 0];
  const dc = [0, 0, -1, 1];

  const START = 'S';
  const LEVER = 'L';
  const EXIT = 'E';
  const WALL = 'X';

  const isValid = (r, c) => r >= 0 && r < numRows && c >= 0 && c < numCols;

  const getDistance = (startChar, endChar) => {
    const queue = [];
    const visited = Array.from({ length: numRows }, () =>
      Array(numCols).fill(false)
    );

    for (let i = 0; i < numRows; i++) {
      for (let j = 0; j < numCols; j++) {
        if (maps[i][j] === startChar) {
          queue.push([i, j, 0]);
          visited[i][j] = true;
        }
      }
    }

    while (queue.length > 0) {
      const [r, c, dist] = queue.shift();

      if (maps[r][c] === endChar) return dist;

      for (let d = 0; d < 4; d++) {
        const nr = r + dr[d];
        const nc = c + dc[d];

        if (!isValid(nr, nc)) continue;
        if (visited[nr][nc]) continue;
        if (maps[nr][nc] === WALL) continue;

        visited[nr][nc] = true;
        queue.push([nr, nc, dist + 1]);
      }
    }

    return -1;
  };

  const startToLever = getDistance(START, LEVER);
  const leverToExit = getDistance(LEVER, EXIT);

  if (startToLever === -1 || leverToExit === -1) return -1;
  return startToLever + leverToExit;
}
