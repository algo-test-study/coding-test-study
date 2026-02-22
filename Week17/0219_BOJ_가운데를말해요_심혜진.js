// BOJ 1655 제출용 (Node.js)
const fs = require("fs");
const data = fs.readFileSync(0, "utf8").trim().split(/\s+/);
let idx = 0;
const N = Number(data[idx++]);

class Heap {
  constructor(compare) {
    this.a = [];
    this.compare = compare;
  }
  size() { return this.a.length; }
  peek() { return this.a[0]; }
  push(v) {
    const a = this.a;
    a.push(v);
    let i = a.length - 1;
    while (i > 0) {
      const p = (i - 1) >> 1;
      if (this.compare(a[p], a[i])) break;
      [a[p], a[i]] = [a[i], a[p]];
      i = p;
    }
  }
  pop() {
    const a = this.a;
    const n = a.length;
    if (n === 0) return undefined;
    const top = a[0];
    const last = a.pop();
    if (n > 1) {
      a[0] = last;
      let i = 0;
      while (true) {
        let l = i * 2 + 1;
        let r = l + 1;
        let best = i;

        if (l < a.length && !this.compare(a[best], a[l])) best = l;
        if (r < a.length && !this.compare(a[best], a[r])) best = r;

        if (best === i) break;
        [a[i], a[best]] = [a[best], a[i]];
        i = best;
      }
    }
    return top;
  }
}

const left = new Heap((p, c) => p >= c); // max-heap
const right = new Heap((p, c) => p <= c); // min-heap

let out = [];

for (let i = 0; i < N; i++) {
  const x = Number(data[idx++]);

  if (left.size() === right.size()) left.push(x);
  else right.push(x);

  if (right.size() > 0 && left.peek() > right.peek()) {
    const a = left.pop();
    const b = right.pop();
    left.push(b);
    right.push(a);
  }

  out.push(String(left.peek()));
}

process.stdout.write(out.join("\n"));