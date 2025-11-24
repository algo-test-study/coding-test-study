const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split('\n');

let line = 0;
const [N, M] = input[line++].split(' ').map(Number);
let [r, c, d] = input[line++].split(' ').map(Number);

const room = [];
for (let i = 0; i < N; i++) {
  room.push(input[line++].split(' ').map(Number));
}

const dr = [-1, 0, 1, 0];
const dc = [0, 1, 0, -1];

let cleaned = 0;

while (true) {
  if (room[r][c] === 0) {
    room[r][c] = 2;
    cleaned++;
  }

  let moved = false;
  for (let i = 0; i < 4; i++) {
    d = (d + 3) % 4;
    const nr = r + dr[d];
    const nc = c + dc[d];

    if (nr >= 0 && nr < N && nc >= 0 && nc < M && room[nr][nc] === 0) {
      r = nr;
      c = nc;
      moved = true;
      break;
    }
  }

  if (moved) continue;

  const backDir = (d + 2) % 4;
  const br = r + dr[backDir];
  const bc = c + dc[backDir];

  if (br < 0 || br >= N || bc < 0 || bc >= M || room[br][bc] === 1) {
    break;
  }

  r = br;
  c = bc;
}

console.log(cleaned);
