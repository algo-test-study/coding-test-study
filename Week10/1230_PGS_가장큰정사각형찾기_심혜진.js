function solution(board) {
  const n = board.length;
  const m = board[0].length;
  let maxSide = 0;

  for (let i=0; i<n; i++) if (board[i][0] === 1) maxSide = 1;
  for (let j=0; j<m; j++) if (board[0][j] === 1) maxSide = 1;

  for (let i=1; i<n; i++) {
    for (let j=1; j<m; j++) {
      if (board[i][j] === 1) {
        const up = board[i - 1][j];
        const left = board[i][j-1];
        const diag = board[i-1][j-1];
        board[i][j] = Math.min(up, left, diag) + 1;
        if (board[i][j] > maxSide) maxSide = board[i][j];
      }
    }
  }

  return maxSide*maxSide;
}
