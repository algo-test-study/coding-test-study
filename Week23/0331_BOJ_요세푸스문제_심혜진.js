const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split(' ');
const n = parseInt(input[0]);
const k = parseInt(input[1]);

class Queue{
  items = [];
  front = 0;
  rear = 0;
  push(item) {
      this.items.push(item);
      this.rear++;
  }
  size(){
      return this.rear - this.front;
  }
  pop() {
    if(this.front > 5000) {
      this.items = this.items.slice(this.front);
      this.rear -= this.front;
      this.front = 0;
    }
      return this.items[this.front++];
  }
}

function solution(n,k){
  let result = [];
  let queue = new Queue();
  for(let i=1; i<n+1; i++){
    queue.push(i);
  }
  while(queue.size() !== 1){
    for(let i=0; i<k-1; i++){
      queue.push(queue.pop());
    }
    result.push(queue.pop());
  }
  result.push(queue.pop());
  return result;
}

const answer = solution(n, k);
console.log('<' + answer.join(', ') + '>');