function solution(board, moves) {
    const basket = [];
    let answer = 0;

    for (const move of moves) {
        const col = move - 1;

        for (let row = 0; row < board.length; row++) {
            if (board[row][col] === 0) continue;

            const doll = board[row][col];
            board[row][col] = 0;

            if (basket[basket.length - 1] === doll) {
                basket.pop();
                answer += 2;
            } else {
                basket.push(doll);
            }

            break;
        }
    }

    return answer;
}