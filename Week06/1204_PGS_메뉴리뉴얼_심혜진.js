function solution(orders, course) {
  const answer = [];

  function getCombinations(chars, len) {
    const result = [];

    function dfs(start, picked) {
      if (picked.length === len) {
        result.push(picked.join(""));
        return;
      }

      for (let i = start; i < chars.length; i++) {
        picked.push(chars[i]);
        dfs(i + 1, picked);
        picked.pop();
      }
    }

    dfs(0, []);
    return result;
  }

  for (const size of course) {
    const frequency = {};

    for (const order of orders) {
      const chars = order.split("").sort();

      if (chars.length < size) continue;

      const combos = getCombinations(chars, size);
      for (const combo of combos) {
        frequency[combo] = (frequency[combo] || 0) + 1;
      }
    }

    let maxCount = 0;

    for (const key in frequency) {
      const count = frequency[key];
      if (count >= 2 && count > maxCount) {
        maxCount = count;
      }
    }

    if (maxCount >= 2) {
      for (const key in frequency) {
        if (frequency[key] === maxCount) {
          answer.push(key);
        }
      }
    }
  }

  return answer.sort();
}

