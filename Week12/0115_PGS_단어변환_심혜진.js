function solution(begin, target, words) {
  if (!words.includes(target)) return 0;

  function isOneDiff(a, b) {
    let diff = 0;
    for (let i = 0; i < a.length; i++) {
      if (a[i] !== b[i]) diff++;
      if (diff > 1) return false;
    }
    return diff === 1;
  }

  const visited = new Array(words.length).fill(false);
  const queue = [[begin, 0]];

  while (queue.length) {
    const [cur, count] = queue.shift();

    if (cur === target) return count;

    for (let i = 0; i < words.length; i++) {
      if (!visited[i] && isOneDiff(cur, words[i])) {
        visited[i] = true;
        queue.push([words[i], count + 1]);
      }
    }
  }

  return 0;
}
