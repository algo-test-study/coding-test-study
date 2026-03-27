function solution(land) {
  const n=land.length;
  const dp=Array.from({length: n},() => Array(4).fill(0));

  for (let j=0; j<4; j++) {
      dp[0][j]=land[0][j];
  }

  for (let i=1; i<n; i++) {
      for (let j=0; j<4; j++) {
          let maxPrev = 0;
          for (let k=0; k<4; k++) {
              if (k!==j) {
                  maxPrev=Math.max(maxPrev, dp[i-1][k]);
              }
          }
          dp[i][j]==land[i][j]+maxPrev;
      }
  }

  return Math.max(...dp[n-1]);
}
