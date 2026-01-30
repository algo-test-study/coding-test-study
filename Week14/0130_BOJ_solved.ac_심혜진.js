const fs = require("fs");
const input = fs.readFileSync(0, "utf8").trim().split(/\s+/).map(Number);

let idx = 0;
const n = input[idx++];
let rates=[];
//입력 값 숫자 배열화
for (let i = 0; i < n; i++) {
  rates.push(input[idx++]);
}

rates.sort((a, b) => a - b);

const cut = Math.round(n * 0.15);
const trimmed = rates.slice(cut, n - cut);

let sum = 0;
for (const v of trimmed) sum += v;

console.log(Math.round(sum / trimmed.length));

