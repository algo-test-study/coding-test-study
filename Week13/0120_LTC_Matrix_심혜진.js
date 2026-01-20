var updateMatrix = function(mat) {
  const m = mat.length;
  const n = mat[0].length;
  const dist = Array.from({ length: m }, () => Array(n).fill(Infinity));
  const queue = [];
  let head = 0;

  for (let i = 0; i < m; i++) {
    for (let j = 0; j < n; j++) {
      if (mat[i][j] === 0) {
        dist[i][j] = 0;
        queue.push([i, j]);
      }
    }
  }

  const dirs = [[1,0], [-1,0], [0,1], [0,-1]];

  while (head < queue.length) {
    const [x, y] = queue[head++];
    for (const [dx, dy] of dirs) {
      const nx = x + dx;
      const ny = y + dy;
      if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
        if (dist[nx][ny] > dist[x][y] + 1) {
          dist[nx][ny] = dist[x][y] + 1;
          queue.push([nx, ny]);
        }
      }
    }
  }

  return dist;
};
