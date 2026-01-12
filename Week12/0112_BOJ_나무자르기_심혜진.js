const fs = require("fs");
const input = fs.readFileSync(0, "utf8").trim().split(/\s+/).map(Number);

let idx = 0;
const N = input[idx++];
const M = input[idx++];

const trees = input.slice(idx, idx + N);

let low = 0;
let high = 0;
for (let i = 0; i < N; i++) {
  if (trees[i] > high) high = trees[i];
}

let answer = 0;

while (low <= high) {
  const mid = Math.floor((low + high) / 2);

  let sum = 0;
  for (let i = 0; i < N; i++) {
    const diff = trees[i] - mid;
    if (diff > 0) sum += diff;
    if (sum >= M) break;
  }

  if (sum >= M) {
    answer = mid;
    low = mid + 1;
  } else {
    high = mid - 1;
  }
}

console.log(answer);