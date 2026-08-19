function solution(rectangle, characterX, characterY, itemX, itemY) {
    const MAX = 101;
    const board = Array.from({ length: MAX }, () => Array(MAX).fill(0));
    const visited = Array.from({ length: MAX }, () => Array(MAX).fill(false));

    const scaledRects = rectangle.map(([x1, y1, x2, y2]) => [
        x1 * 2,
        y1 * 2,
        x2 * 2,
        y2 * 2,
    ]);

    for (const [x1, y1, x2, y2] of scaledRects) {
        for (let x = x1; x <= x2; x++) {
            for (let y = y1; y <= y2; y++) {
                board[x][y] = 1;
            }
        }
    }

    for (const [x1, y1, x2, y2] of scaledRects) {
        for (let x = x1 + 1; x < x2; x++) {
            for (let y = y1 + 1; y < y2; y++) {
                board[x][y] = 0;
            }
        }
    }

    const startX = characterX * 2;
    const startY = characterY * 2;
    const targetX = itemX * 2;
    const targetY = itemY * 2;

    const dx = [1, -1, 0, 0];
    const dy = [0, 0, 1, -1];

    const queue = [];
    let head = 0;

    queue.push([startX, startY, 0]);
    visited[startX][startY] = true;

    while (head < queue.length) {
        const [x, y, dist] = queue[head++];

        if (x === targetX && y === targetY) {
            return dist / 2;
        }

        for (let dir = 0; dir < 4; dir++) {
            const nx = x + dx[dir];
            const ny = y + dy[dir];

            if (nx < 0 || ny < 0 || nx >= MAX || ny >= MAX) continue;
            if (visited[nx][ny]) continue;
            if (board[nx][ny] !== 1) continue;

            visited[nx][ny] = true;
            queue.push([nx, ny, dist + 1]);
        }
    }

    return 0;
}
