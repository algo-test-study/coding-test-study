const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split(/\s+/);

let idx = 0;
const N = Number(input[idx++]);
const map = Array.from({ length: N }, () => Array(N).fill(0));

let maxHeight = 0;
for (let i = 0; i < N; i++) {
  for (let j = 0; j < N; j++) {
    map[i][j] = Number(input[idx++]);
    if (map[i][j] > maxHeight) maxHeight = map[i][j];
  }
}

const dx = [-1, 1, 0, 0];
const dy = [0, 0, -1, 1];

let answer = 1;

function dfs(x, y, height, visited) {
  const stack = [[x, y]];
  visited[x][y] = true;

  while (stack.length) {
    const [cx, cy] = stack.pop();
    for (let d = 0; d < 4; d++) {
      const nx = cx + dx[d];
      const ny = cy + dy[d];
      if (
        nx >= 0 && nx < N &&
        ny >= 0 && ny < N &&
        !visited[nx][ny] &&
        map[nx][ny] > height
      ) {
        visited[nx][ny] = true;
        stack.push([nx, ny]);
      }
    }
  }
}

for (let h = 0; h <= maxHeight; h++) {
  let visited = Array.from({ length: N }, () => Array(N).fill(false));
  let count = 0;

  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (!visited[i][j] && map[i][j] > h) {
        dfs(i, j, h, visited);
        count++;
      }
    }
  }
  if (count > answer) answer = count;
}

console.log(answer);