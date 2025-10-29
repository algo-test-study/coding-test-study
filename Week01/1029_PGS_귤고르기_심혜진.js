function solution(k, tangerine) {
  const countMap = new Map();

  for (const size of tangerine) {
    countMap.set(size, (countMap.get(size) || 0) + 1);
  }

  const sortedCounts = Array.from(countMap.values()).sort((a, b) => b-a);

  let remaining = k;
  let types = 0;

  for (const count of sortedCounts) {
    if (remaining <= 0) break;
    types++;
    remaining -= count;
  }

  return types;
}