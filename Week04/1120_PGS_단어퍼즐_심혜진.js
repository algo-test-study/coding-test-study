function solution(strs, t) {
  const INF = 1e9;
  const n = t.length;
  const dp = Array(n+1).fill(INF);
  dp[0] = 0;

  for (let i=1; i<=n; i++) {
      for (const word of strs) {
          const len = word.length;
          if (i-len >= 0 && t.slice(i-len, i) === word) {
              dp[i] = Math.min(dp[i], dp[i-len] + 1);
          }
      }
  }

  return dp[n] === INF ? -1 : dp[n];
}
