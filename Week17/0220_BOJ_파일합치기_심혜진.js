'use strict';
const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
let idx = 0;

class MinHeap {
  constructor() {
    this.a = [];
  }
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
    const n = a.length;
    if (n === 0) return null;
    const top = a[0];
    const last = a.pop();
    if (n > 1) {
      a[0] = last;
      let i = 0;
      while (true) {
        const l = i * 2 + 1;
        const r = l + 1;
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

const T = Number(input[idx++]);
let out = [];

for (let tc = 0; tc < T; tc++) {
  const K = Number(input[idx++]);
  const heap = new MinHeap();

  for (let i = 0; i < K; i++) {
    // 파일 크기: BigInt로 저장
    heap.push(BigInt(input[idx++]));
  }

  let cost = 0n;
  while (heap.size() > 1) {
    const x = heap.pop();
    const y = heap.pop();
    const s = x + y;
    cost += s;
    heap.push(s);
  }

  out.push(cost.toString());
}

process.stdout.write(out.join('\n'));