const fs = require('fs');
const path = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';

const [[v, e], ...input] = fs
  .readFileSync(path)
  .toString()
  .trim()
  .split('\n')
  .map(line => line.split(' ').map(Number));

input.sort((a, b) => a[2] - b[2]);

const parent = Array.from({ length: v + 1 }, (_, i) => i);

const rank = Array(v + 1).fill(0);

const find = (x) => {
  if (parent[x] !== x) {
    parent[x] = find(parent[x]);
  }
  return parent[x];
};

// union (rank 사용)
const union = (a, b) => {
  const rootA = find(a);
  const rootB = find(b);

  if (rootA === rootB) return false;

  if (rank[rootA] < rank[rootB]) {
    parent[rootA] = rootB;
  } else if (rank[rootA] > rank[rootB]) {
    parent[rootB] = rootA;
  } else {
    parent[rootB] = rootA;
    rank[rootA]++;
  }

  return true;
};

let total = 0;
let count = 0;

for (const [a, b, cost] of input) {
  if (union(a, b)) {
    total += cost;
    count++;

    if (count === v - 1) break;
  }
}

console.log(total);