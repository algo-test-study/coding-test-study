const fs = require("fs");
const input = fs.readFileSync(0, "utf8").trim().split(/\s+/).map(Number);

let idx = 0;
const N = input[idx++];

class MinHeap {
  constructor() { this.a = []; }
  size() { return this.a.length; }

  push(x) {
    const a = this.a;
    a.push(x);
    let i = a.length - 1;
    while (i > 0) {
      const p = (i - 1) >> 1;
      if (a[p] <= a[i]) break;
      [a[p], a[i]] = [a[i], a[p]];
      i = p;
    }
  }

  pop() {
    const a = this.a;
    if (a.length === 0) return null;
    const top = a[0];
    const last = a.pop();
    if (a.length > 0) {
      a[0] = last;
      let i = 0;
      while (true) {
        let l = i * 2 + 1;
        let r = l + 1;
        let m = i;
        if (l < a.length && a[l] < a[m]) m = l;
        if (r < a.length && a[r] < a[m]) m = r;
        if (m === i) break;
        [a[i], a[m]] = [a[m], a[i]];
        i = m;
      }
    }
    return top;
  }
}

if (!N || N <= 1) {
  console.log("0");
} else {
  const heap = new MinHeap();
  for (let i = 0; i < N; i++) heap.push(input[idx++]);

  let ans = 0;
  while (heap.size() >= 2) {
    const x = heap.pop();
    const y = heap.pop();
    const s = x + y;
    ans += s;
    heap.push(s);
  }
  console.log(String(ans));
}