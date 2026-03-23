function solution(n, wires) {
  const g = Array.from({ length: n + 1 }, () => []);
  wires.forEach(([a, b]) => (g[a].push(b), g[b].push(a)));

  const count = (s, A, B) => {
    const stack = [s], v = Array(n + 1).fill(false);
    v[s] = true; let c = 0;
    while (stack.length) {
      const x = stack.pop(); c++;
      for (const y of g[x]) {
        if ((x === A && y === B) || (x === B && y === A) || v[y]) continue;
        v[y] = true; stack.push(y);
      }
    }
    return c;
  };

  let ans = n;
  for (const [a, b] of wires) ans = Math.min(ans, Math.abs(n - 2 * count(a, a, b)));
  return ans;
}
