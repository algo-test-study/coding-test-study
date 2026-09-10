var solveNQueens = function (n) {
  const result = [];
  const board = Array.from({ length: n }, () => ".".repeat(n));

  const cols = new Set();      // 사용한 열
  const diag1 = new Set();     // r - c (↘ 방향 대각선)
  const diag2 = new Set();     // r + c (↙ 방향 대각선)

  function backtrack(row) {
    if (row === n) {
      result.push([...board]);
      return;
    }

    for (let col = 0; col < n; col++) {
      if (cols.has(col) || diag1.has(row - col) || diag2.has(row + col)) {
        continue;
      }

      cols.add(col);
      diag1.add(row - col);
      diag2.add(row + col);

      const line = board[row].split("");
      line[col] = "Q";
      board[row] = line.join("");

      backtrack(row + 1);

      cols.delete(col);
      diag1.delete(row - col);
      diag2.delete(row + col);
      const reset = board[row].split("");
      reset[col] = ".";
      board[row] = reset.join("");
    }
  }

  backtrack(0);
  return result;
};
