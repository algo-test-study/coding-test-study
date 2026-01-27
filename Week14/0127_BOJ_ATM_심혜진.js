const fs = require('fs');
const input = fs.readFileSync(0, 'utf-8').trim().split(/\s+/);

let n = Number(input[0]);
let arr = input.slice(1).map(Number);

arr.sort((a,b)=> a-b);

let reduced = 0;
let answer = 0;

for(let i=0; i<n; i++){
    reduced +=arr[i];

    answer += reduced;
}

console.log(answer);
