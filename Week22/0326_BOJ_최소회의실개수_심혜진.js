const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split('\n');
const N = Number(input[0]);

const meetings = [];
for (let i = 1; i <= N; i++) {
  const [s, e] = input[i].split(' ').map(Number);
  meetings.push([s, e]);
}

meetings.sort((a, b) => a[0] - b[0]);

class MinHeap {
  constructor() {
    this.heap = [];
  }

  push(value) {
    this.heap.push(value);
    this._bubbleUp(this.heap.length - 1);
  }

  pop() {
    if (this.heap.length === 0) return null;
    if (this.heap.length === 1) return this.heap.pop();

    const min = this.heap[0];
    this.heap[0] = this.heap.pop();
    this._bubbleDown(0);
    return min;
  }

  peek() {
    return this.heap.length === 0 ? null : this.heap[0];
  }

  size() {
    return this.heap.length;
  }

  _bubbleUp(idx) {
    while (idx > 0) {
      const parent = Math.floor((idx - 1) / 2);
      if (this.heap[parent] <= this.heap[idx]) break;
      [this.heap[parent], this.heap[idx]] = [this.heap[idx], this.heap[parent]];
      idx = parent;
    }
  }

  _bubbleDown(idx) {
    const length = this.heap.length;
    while (true) {
      let left = idx * 2 + 1;
      let right = idx * 2 + 2;
      let smallest = idx;

      if (left < length && this.heap[left] < this.heap[smallest]) {
        smallest = left;
      }
      if (right < length && this.heap[right] < this.heap[smallest]) {
        smallest = right;
      }
      if (smallest === idx) break;
      [this.heap[idx], this.heap[smallest]] = [this.heap[smallest], this.heap[idx]];
      idx = smallest;
    }
  }
}

const heap = new MinHeap();

heap.push(meetings[0][1]);

for (let i = 1; i < N; i++) {
  const [start, end] = meetings[i];
  const earliestEnd = heap.peek();

  if (earliestEnd <= start) {
    heap.pop();
  }
  heap.push(end);
}

console.log(heap.size());
