function solution(n,times) {
  let left=0;
  let right=Math.max(...times) * n;
  let answer=right;
  while (left<=right) {
    const mid = Math.floor((left+right)/2);
    let totalPeople=0;
    for (const time of times) {
      totalPeople+=Math.floor(mid/time);
    }

    if (totalPeople>=n) {
      answer=mid;
      right=mid-1;
    } else {
      left=mid+1;
    }
  }
  return answer;
}