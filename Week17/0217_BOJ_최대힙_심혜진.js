// BOJ 11279 - 최대 힙 (Node.js)

class MaxHeap {
  constructor() {
    this.heap = [];
  }

  size() {
    return this.heap.length;
  }

  peek() {
    return this.heap[0];
  }

  push(value) {
    this.heap.push(value);
    this._bubbleUp(this.heap.length - 1);
  }

  pop() {
    const n = this.heap.length;
    if (n === 0) return 0;          // 비어있으면 0 출력
    if (n === 1) return this.heap.pop();

    const max = this.heap[0];
    this.heap[0] = this.heap.pop(); // 마지막을 루트로 올리고
    this._bubbleDown(0);            // 내려가며 정리
    return max;
  }

  _bubbleUp(idx) {
    while (idx > 0) {
      const parent = Math.floor((idx - 1) / 2);
      if (this.heap[parent] >= this.heap[idx]) break; // (최대 힙) 부모가 더 크면 OK
      [this.heap[parent], this.heap[idx]] = [this.heap[idx], this.heap[parent]];
      idx = parent;
    }
  }

  _bubbleDown(idx) {
    const n = this.heap.length;
    while (true) {
      const left = idx * 2 + 1;
      const right = idx * 2 + 2;
      let largest = idx;

      if (left < n && this.heap[left] > this.heap[largest]) largest = left;
      if (right < n && this.heap[right] > this.heap[largest]) largest = right;

      if (largest === idx) break;
      [this.heap[idx], this.heap[largest]] = [this.heap[largest], this.heap[idx]];
      idx = largest;
    }
  }
}

const fs = require("fs");
const input = fs.readFileSync(0, "utf8").trim().split("\n").map(Number);

const N = input[0];
const heap = new MaxHeap();
let out = [];

for (let i = 1; i <= N; i++) {
  const x = input[i];
  if (x === 0) out.push(heap.pop());
  else heap.push(x);
}

process.stdout.write(out.join("\n"));