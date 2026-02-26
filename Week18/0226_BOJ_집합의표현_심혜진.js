// Main.js
const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split('\n');

const [n, m] = input[0].split(' ').map(Number);
const parent = Array(n + 1);

for (let i = 0; i <= n; i++) {
  parent[i] = i;
}

function find(x) {
  if (parent[x] === x) return x;
  parent[x] = find(parent[x]);
  return parent[x];
}

function union(a, b) {
  a = find(a);
  b = find(b);
  if (a === b) return;
  if (a < b) parent[b] = a;
  else parent[a] = b;
}

let ans = [];
for (let i = 1; i <= m; i++) {
  const [op, a, b] = input[i].split(' ').map(Number);
  if (op === 0) {
    union(a, b);
  } else {
    ans.push(find(a) === find(b) ? 'YES' : 'NO');
  }
}

console.log(ans.join('\n'));
