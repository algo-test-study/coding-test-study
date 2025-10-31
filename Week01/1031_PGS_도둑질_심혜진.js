function solution(money) {
  const n = money.length;
  const firstDp = Array(n).fill(0);
  const secondDp = Array(n).fill(0);

  firstDp[0] = money[0];
  firstDp[1] = money[0];
  for (let i=2; i < n-1; i++) {
    firstDp[i] = Math.max(firstDp[i-1], firstDp[i-2] + money[i]);
  }

  secondDp[1] = money[1];
  for (let i=2; i<n; i++) {
    secondDp[i] = Math.max(secondDp[i-1], secondDp[i-2] + money[i]);
  }
  const answer = Math.max(firstDp[n-2], secondDp[n-1]);

  return answer;
}
