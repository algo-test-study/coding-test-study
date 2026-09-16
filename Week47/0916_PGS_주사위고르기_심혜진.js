function solution(dice) {
  const n = dice.length;
  const half = n / 2;
  let bestWin = -1;
  let bestSet = [];

  function dfsComb(start, picked) {
    if (picked.length === half) {
      evaluate(picked);
      return;
    }
    for (let i = start; i < n; i++) {
      picked.push(i);
      dfsComb(i + 1, picked);
      picked.pop();
    }
  }

  function getAllSums(diceIndexes) {
    let sums = [0];
    for (const idx of diceIndexes) {
      const faces = dice[idx];
      const newSums = [];
      for (const s of sums) {
        for (const face of faces) {
          newSums.push(s + face);
        }
      }
      sums = newSums;
    }
    return sums;
  }

  function evaluate(aIndexes) {
    const isA = Array(n).fill(false);
    for (const i of aIndexes) isA[i] = true;

    const bIndexes = [];
    for (let i = 0; i < n; i++) {
      if (!isA[i]) bIndexes.push(i);
    }

    const sumA = getAllSums(aIndexes);
    const sumB = getAllSums(bIndexes);
    sumB.sort((x, y) => x - y);

    function countLessThan(target) {
      let left = 0;
      let right = sumB.length;
      while (left < right) {
        const mid = (left + right) >> 1;
        if (sumB[mid] < target) left = mid + 1;
        else right = mid;
      }
      return left;
    }

    let win = 0;
    for (const a of sumA) {
      win += countLessThan(a);
    }

    if (win > bestWin) {
      bestWin = win;
      bestSet = aIndexes.map(i => i + 1);
    }
  }

  dfsComb(0, []);

  return bestSet;
}
