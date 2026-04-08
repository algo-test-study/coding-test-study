function solution(str1, str2) {
  function makeBigrams(str) {
    const result = [];
    const lower = str.toLowerCase();

    for (let i = 0; i < lower.length - 1; i++) {
      const a = lower[i];
      const b = lower[i + 1];

      if (a >= 'a' && a <= 'z' && b >= 'a' && b <= 'z') {
        result.push(a + b);
      }
    }

    return result;
  }

  function makeCountMap(arr) {
    const map = {};
    for (const item of arr) {
      if (map[item] === undefined) {
        map[item] = 1;
      } else {
        map[item]++;
      }
    }
    return map;
  }

  const bigrams1 = makeBigrams(str1);
  const bigrams2 = makeBigrams(str2);

  const map1 = makeCountMap(bigrams1);
  const map2 = makeCountMap(bigrams2);

  let intersectionCount = 0;
  let unionCount = 0;

  const allKeys = new Set([...Object.keys(map1), ...Object.keys(map2)]);

  allKeys.forEach((key) => {
    const count1 = map1[key] || 0;
    const count2 = map2[key] || 0;

    const minCount = Math.min(count1, count2);
    const maxCount = Math.max(count1, count2);

    intersectionCount += minCount;
    unionCount += maxCount;
  });

  if (unionCount === 0) {
    return 65536;
  }

  const jaccard = intersectionCount / unionCount;
  return Math.floor(jaccard * 65536);
}
