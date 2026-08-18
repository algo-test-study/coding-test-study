function solution(n, costs) {
    costs.sort((a, b) => a[2] - b[2]);

    const parent = Array.from({ length: n }, (_, i) => i);

    const find = (x) => {
        if (parent[x] === x) return x;
        parent[x] = find(parent[x]);
        return parent[x];
    };

    const union = (a, b) => {
        const rootA = find(a);
        const rootB = find(b);
        if (rootA === rootB) return false;
        if (rootA < rootB) parent[rootB] = rootA;
        else parent[rootA] = rootB;
        return true;
    };

    let answer = 0;
    let edgesUsed = 0;

    for (const [u, v, cost] of costs) {
        if (union(u, v)) {
            answer += cost;
            edgesUsed += 1;
            if (edgesUsed === n - 1) break;
        }
    }

    return answer;
}
