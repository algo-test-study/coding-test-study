const fs = require('fs');
const input = fs.readFileSync(0, 'utf-8').trim().split(/\s+/);

let idx = 0;
const n = Number(input[idx++]);
const arr = [];

for (let i = 0; i < n; i++) {
  const s = Number(input[idx++]);
  const t = Number(input[idx++]);
  arr.push([s, t]);
}

arr.sort((a, b) => a[0] - b[0]);

class MinHeap {
  constructor() {
    this.heap = [];
  }

  push(val) {
    this.heap.push(val);
    let i = this.heap.length - 1;
    while (i > 0) {
      let p = Math.floor((i - 1) / 2);
      if (this.heap[p] <= this.heap[i]) break;
      [this.heap[p], this.heap[i]] = [this.heap[i], this.heap[p]];
      i = p;
    }
  }

  pop() {
    if (this.heap.length === 1) return this.heap.pop();
    const top = this.heap[0];
    this.heap[0] = this.heap.pop();
    let i = 0;
    while (true) {
      let l = i * 2 + 1;
      let r = i * 2 + 2;
      let smallest = i;

      if (l < this.heap.length && this.heap[l] < this.heap[smallest]) {
        smallest = l;
      }
      if (r < this.heap.length && this.heap[r] < this.heap[smallest]) {
        smallest = r;
      }
      if (smallest === i) break;
      [this.heap[i], this.heap[smallest]] = [this.heap[smallest], this.heap[i]];
      i = smallest;
    }
    return top;
  }

  peek() {
    return this.heap[0];
  }

  size() {
    return this.heap.length;
  }
}

const heap = new MinHeap();

for (const [start, end] of arr) {
  if (heap.size() > 0 && heap.peek() <= start) {
    heap.pop();
  }
  heap.push(end);
}

console.log(heap.size());
