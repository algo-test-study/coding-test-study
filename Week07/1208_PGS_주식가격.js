function solution(prices) {
  let answer = [];
  let n = prices.length;

  let stack = [];
  for(let i=0; i<n; i++) {
      while(stack.length > 0 && prices[i] < prices[stack[stack.length-1]]) {
          let j = stack.pop();
          answer[j] = i-j;
      }
      stack.push(i);//0 1 2(제거됨) 3 4
  }

  for(let i=0; i<stack.length; i++){
      answer[stack[i]] = n-stack[i]-1;
  }
  return answer;
}

console.log(solution([1,2,3,2,3]));