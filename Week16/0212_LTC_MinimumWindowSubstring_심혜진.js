/**
 * 가변길이 슬라이딩 윈도우 문제 - 해시기반 딕셔너리, 값 저장하는 셋, need, required, formed, window, while(valid) left++  
  Example 1:
  Input: s = "ADOBECODEBANC", t = "ABC"
  Output: "BANC"
  Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
  Example 2:

  Input: s = "a", t = "a"
  Output: "a"
  Explanation: The entire string s is the minimum window.
  Example 3:

  Input: s = "a", t = "aa"
  Output: ""
  Explanation: Both 'a's from t must be included in the window.
  Since the largest window of s only has one 'a', return empty string.
 * @param {string} s
 * @param {string} t
 * @return {string}
 */
var minWindow = function (s, t) {
  if (t.length === 0 || s.length === 0) return "";

  const need = new Map();
  for (const ch of t) need.set(ch, (need.get(ch) || 0) + 1);

  const window = new Map();
  const required = need.size;
  let formed = 0;

  let left = 0;
  let bestLen = Infinity;
  let bestL = 0;

  for (let right = 0; right < s.length; right++) {
    const c = s[right];
    window.set(c, (window.get(c) || 0) + 1);

    if (need.has(c) && window.get(c) === need.get(c)) {
      formed++;
    }

    while (formed === required) {
      const curLen = right - left + 1;
      if (curLen < bestLen) {
        bestLen = curLen;
        bestL = left;
      }

      const lc = s[left];
      window.set(lc, window.get(lc) - 1);

      if (need.has(lc) && window.get(lc) < need.get(lc)) {
        formed--;
      }

      left++;
    }
  }

  return bestLen === Infinity ? "" : s.slice(bestL, bestL + bestLen);
};
