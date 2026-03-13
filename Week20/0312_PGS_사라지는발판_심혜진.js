function solution(board, aloc, bloc) {
  const ROW = board.length;
  const COL = board[0].length;

  const DR = [-1, 0, 1, 0];
  const DC = [0, 1, 0, -1];

  function isValidPos(r, c) {
    return 0 <= r && r < ROW && 0 <= c && c < COL;
  }

  function recursiveFunc(alphaPos, betaPos, visited, step) {
    const [r, c] = step % 2 === 0 ? alphaPos : betaPos;
    let canMove = false;
    let isOpponentWinner = true;

    const winSteps = [];
    const loseSteps = [];

    for (let i = 0; i < 4; i++) {
      const nr = r + DR[i];
      const nc = c + DC[i];

      if (isValidPos(nr, nc) && !visited.has(`${nr},${nc}`) && board[nr][nc]) {
        canMove = true;
        if (alphaPos[0] === betaPos[0] && alphaPos[1] === betaPos[1]) {
          return [true, step + 1];
        }

        const [win, stepsLeft] = step % 2 === 0
          ? recursiveFunc([nr, nc], betaPos, new Set([...visited, `${r},${c}`]), step + 1)
          : recursiveFunc(alphaPos, [nr, nc], new Set([...visited, `${r},${c}`]), step + 1);

        isOpponentWinner &= win;

        if (win) {
          winSteps.push(stepsLeft);
        } else {
          loseSteps.push(stepsLeft);
        }
      }
    }

    if (!canMove) {
      return [false, step];
    }

    if (isOpponentWinner) {
      return [false, Math.max(...winSteps)];
    }

    return [true, Math.min(...loseSteps)];
  }

  const [_, steps] = recursiveFunc(aloc, bloc, new Set(), 0);

  return steps;
}