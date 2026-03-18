const fs = require('fs');
const path = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
const [rc, ...inputs] = fs.readFileSync(path).toString().trim().split('\n');
const map = inputs.map((it) => it.split(''));
const [r, c] = rc.split(' ').map(Number);
const dx = [1, -1, 0, 0];
const dy = [0, 0, 1, -1];
let answer = -1;

const bfs = (x, y) => {
  const visited = Array.from({ length: r }, () => Array(c).fill(false));
  const q = [[x, y]];
  let front = 0;

  visited[x][y] = 1;

  while (front < q.length) {
    const [x, y] = q[front++];
    for (let i = 0; i < 4; i++) {
      const nx = x + dx[i];
      const ny = y + dy[i];

      if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
      if (map[nx][ny] === 'W') continue;
      if (!visited[nx][ny]) {
        visited[nx][ny] = visited[x][y] + 1;
        answer = Math.max(visited[nx][ny], answer);
        q.push([nx, ny]);
      }
    }
  }
};

for (let i = 0; i < r; i++) {
  for (let j = 0; j < c; j++) {
    if (map[i][j] === 'L') bfs(i, j);
  }
}

console.log(answer - 1);