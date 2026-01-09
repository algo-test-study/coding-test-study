function solution(n, times) {
  let low = 0;
  let high = Math.max(...times) * n;
  let answer = high;

  while (low <= high) {
    const mid = Math.floor((low + high) / 2);

    let processed = 0;
    for (const t of times) {
      processed += Math.floor(mid / t);
      if (processed >= n) break;
    }

    if (processed >= n) {
      answer = mid;
      high = mid - 1;
    } else {
      low = mid + 1;
    }
  }

  return answer;
}
