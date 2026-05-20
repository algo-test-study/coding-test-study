function solution(sequence, k) {
  let left = 0;
  let right = 0;
  let sum = sequence[0];

  let bestStart = 0;
  let bestEnd = sequence.length - 1;
  let bestLen = Infinity;

  while (left <= right && right < sequence.length) {
    if (sum === k) {
      const len = right - left + 1;
      if (len < bestLen) {
        bestLen = len;
        bestStart = left;
        bestEnd = right;
      }

      sum -= sequence[left];
      left += 1;
    } else if (sum < k) {
      right += 1;
      if (right < sequence.length) {
        sum += sequence[right];
      }
    } else {
      sum -= sequence[left];
      left += 1;
    }
  }

  return [bestStart, bestEnd];
}
