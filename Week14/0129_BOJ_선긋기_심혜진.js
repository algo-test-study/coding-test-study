const fs = require("fs");
const input = fs.readFileSync(0, "utf8").trim().split(/\s+/).map(Number);

let idx = 0;
const N = input[idx++];

const segs = new Array(N);
for (let i = 0; i < N; i++) {
  const a = input[idx++], b = input[idx++];
  const s = Math.min(a, b);
  const e = Math.max(a, b);
  segs[i] = [s, e];
}

segs.sort((x, y) => (x[0] - y[0]) || (x[1] - y[1]));

let curL = segs[0][0];
let curR = segs[0][1];
let ans = 0;

for (let i = 1; i < N; i++) {
  const [l, r] = segs[i];

  if (l <= curR) {
    // 겹치거나 맞닿음
    if (r > curR) curR = r;
  } else {
    // 분리됨 -> 이전 구간 길이 확정
    ans += (curR - curL);
    curL = l;
    curR = r;
  }
}

ans += (curR - curL);
console.log(ans.toString());
