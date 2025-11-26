function solution(str1, str2) {
  function buildPairs(text) {
      const pairs = new Map();
      const upper = text.toUpperCase();

      for (let i = 0; i < upper.length - 1; i++) {
          const a = upper[i];
          const b = upper[i + 1];

          if (/[A-Z]/.test(a) && /[A-Z]/.test(b)) {
              const key = a + b;
              pairs.set(key, (pairs.get(key) || 0) + 1);
          }
      }

      return pairs;
  }

  const pairs1 = buildPairs(str1);
  const pairs2 = buildPairs(str2);

  if (pairs1.size === 0 && pairs2.size === 0) {
      return 65536;
  }

  let inter = 0;
  let union = 0;

  const keys = new Set([...pairs1.keys(), ...pairs2.keys()]);

  for (const key of keys) {
      const count1 = pairs1.get(key) || 0;
      const count2 = pairs2.get(key) || 0;

      inter += Math.min(count1, count2);
      union += Math.max(count1, count2);
  }

  return Math.floor((inter / union) * 65536);
}
