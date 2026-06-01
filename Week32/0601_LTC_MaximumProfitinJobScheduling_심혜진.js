function jobScheduling(startTime, endTime, profit) {
  const n = startTime.length;
  const jobs = [];

  for (let i = 0; i < n; i++) {
    jobs.push([startTime[i], endTime[i], profit[i]]);
  }

  jobs.sort((a, b) => a[1] - b[1]);

  const ends = jobs.map(job => job[1]);
  const dp = new Array(n + 1).fill(0);

  for (let i = 1; i <= n; i++) {
    const [s, e, p] = jobs[i - 1];
    const j = upperBound(ends, s);
    dp[i] = Math.max(dp[i - 1], dp[j] + p);
  }

  return dp[n];
}

function upperBound(arr, target) {
  let left = 0;
  let right = arr.length;

  while (left < right) {
    const mid = (left + right) >> 1;
    if (arr[mid] <= target) left = mid + 1;
    else right = mid;
  }

  return left;
}
