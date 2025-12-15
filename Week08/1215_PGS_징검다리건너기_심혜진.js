function canCross(stones,k,n) {
  let zeroCheck = 0;
  for(let i=0; i<stones.length; i++) {
    if(stones[i]-n<=0) {
      zeroCheck++;
      if(zeroCheck>k) {
        return false;
      }
    } else {
      zeroCheck = 0;
    }
  }
  return true;
};

function solution(stones, k) {
  let left = 1;//min
  let right = Math.max(...stones);//max
  let answer = 0;

  while(left<=right) {
    let mid = Math.floor((left+right)/2);
    if(canCross(stones,k,mid)) {
      answer=mid;
      left=mid+1;
    } else {
      right=mid-1;
    }
  }

  return answer;
}

console.log(solution([2, 4, 5, 3, 2, 1, 4, 2, 5, 1], 3));