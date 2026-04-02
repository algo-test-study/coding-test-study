function solution(strs, t) {
    const n = t.length;
    const dp = Array(n + 1).fill(Infinity);

    dp[0] = 0;
    for (let i = 0; i < n; i++) {
        for (const s of strs) {
            if (dp[i] === Infinity) continue;
            let j = i + s.length;
            if (j <= n && t.startsWith(s, i)) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
    }

    return dp[n] === Infinity ? -1 : dp[n];
}
