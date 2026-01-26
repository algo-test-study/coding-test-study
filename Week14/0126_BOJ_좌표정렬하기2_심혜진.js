const fs = require('fs');
const input = fs.readFileSync(0, 'utf-8').trim().split(/\s+/);

let idx = 0;
const n = Number(input[idx++]);
const arr = [];

for (let i = 0; i < n; i++) {
  const x = Number(input[idx++]);
  const y = Number(input[idx++]);
  arr.push([x, y]);
}

arr.sort((a, b) => {
  if (a[1] === b[1]) return a[0] - b[0];
  return a[1] - b[1];
});

let out = '';
for (let i = 0; i < n; i++) {
  out += arr[i][0] + ' ' + arr[i][1] + '\n';
}

process.stdout.write(out);
