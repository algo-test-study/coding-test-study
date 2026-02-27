function solution(land, height) {
    const n = land.length;
    const group = Array.from({ length: n }, () => Array(n).fill(-1));
    const dirs = [[1,0],[-1,0],[0,1],[0,-1]];
    let gId = 0;

    const inRange = (x, y) => x >= 0 && x < n && y >= 0 && y < n;

    const bfs = (sx, sy, id) => {
        const q = [[sx, sy]];
        group[sx][sy] = id;
        let head = 0;
        while (head < q.length) {
            const [x, y] = q[head++];
            for (const [dx, dy] of dirs) {
                const nx = x + dx;
                const ny = y + dy;
                if (!inRange(nx, ny)) continue;
                if (group[nx][ny] !== -1) continue;
                if (Math.abs(land[x][y] - land[nx][ny]) <= height) {
                    group[nx][ny] = id;
                    q.push([nx, ny]);
                }
            }
        }
    };

    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (group[i][j] === -1) {
                bfs(i, j, gId++);
            }
        }
    }

    const edges = [];
    for (let x = 0; x < n; x++) {
        for (let y = 0; y < n; y++) {
            for (const [dx, dy] of dirs) {
                const nx = x + dx;
                const ny = y + dy;
                if (!inRange(nx, ny)) continue;
                const a = group[x][y];
                const b = group[nx][ny];
                if (a === b) continue;
                const cost = Math.abs(land[x][y] - land[nx][ny]);
                edges.push([cost, a, b]);
            }
        }
    }

    edges.sort((e1, e2) => e1[0] - e2[0]);

    const parent = Array(gId).fill(0).map((_, i) => i);

    const find = x => {
        if (parent[x] === x) return x;
        parent[x] = find(parent[x]);
        return parent[x];
    };

    const union = (a, b) => {
        a = find(a);
        b = find(b);
        if (a === b) return false;
        if (a < b) parent[b] = a;
        else parent[a] = b;
        return true;
    };

    let answer = 0;
    for (const [cost, a, b] of edges) {
        if (union(a, b)) answer += cost;
    }

    return answer;
}
