const fs = require("fs");
const input = fs.readFileSync(0, "utf8").trim().split("\n");

const [N, C] = input[0].split(" ").map(Number);
const houses = input.slice(1).map(Number).sort((a, b) => a - b);

function canInstall(distance) {
  let count = 1;
  let last = houses[0];

  for (let i = 1; i < N; i++) {
    if (houses[i] - last >= distance) {
      count++;
      last = houses[i];
      if (count >= C) return true;
    }
  }
  return false;
}

let left = 1;
let right = houses[N - 1] - houses[0];
let answer = 0;

while (left <= right) {
  const mid = Math.floor((left + right) / 2);

  if (canInstall(mid)) {
    answer = mid;
    left = mid + 1;
  } else {
    right = mid - 1;
  }
}

console.log(answer);
