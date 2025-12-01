function solution(n, edge) {
    const graph = Array.from({ length: n+1 }, () => []);

    for (const [a,b] of edge) {
        graph[a].push(b);
        graph[b].push(a);
    }

    const distance = Array(n+1).fill(-1);
    const queue = [];
    let head = 0;

    distance[1] = 0;
    queue.push(1);

    while (head < queue.length) {
        const cur = queue[head++];

        for (const next of graph[cur]) {
        if (distance[next] === -1) {
            distance[next] = distance[cur] + 1;
            queue.push(next);
        }
        }
    }

    const maxDist = Math.max(...distance);
    const answer = distance.filter((d) => d === maxDist).length;

    return answer;
}
