function solution(land, height) {
  const n = land.length;
  const group = Array.from({ length: n }, () => Array(n).fill(-1));
  const dirs = [[1,0], [-1,0], [0,1], [0,-1]];

  let groupId = 0;

  // 1. BFS로 height 이하 이동 가능한 구역 나누기
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      if (group[i][j] !== -1) continue;

      const queue = [[i, j]];
      group[i][j] = groupId;
      let head = 0;

      while (head < queue.length) {
        const [x, y] = queue[head++];

        for (const [dx, dy] of dirs) {
          const nx = x + dx;
          const ny = y + dy;

          if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
          if (group[nx][ny] !== -1) continue;

          const diff = Math.abs(land[x][y] - land[nx][ny]);

          if (diff <= height) {
            group[nx][ny] = groupId;
            queue.push([nx, ny]);
          }
        }
      }

      groupId++;
    }
  }

  // 이미 전체가 하나의 구역이면 비용 0
  if (groupId === 1) return 0;

  // 2. 서로 다른 구역 사이의 간선 만들기
  const edges = [];

  for (let x = 0; x < n; x++) {
    for (let y = 0; y < n; y++) {
      for (const [dx, dy] of [[1,0], [0,1]]) {
        const nx = x + dx;
        const ny = y + dy;

        if (nx >= n || ny >= n) continue;

        const a = group[x][y];
        const b = group[nx][ny];

        if (a !== b) {
          const cost = Math.abs(land[x][y] - land[nx][ny]);
          edges.push([cost, a, b]);
        }
      }
    }
  }

  // 3. Kruskal
  edges.sort((a, b) => a[0] - b[0]);

  const parent = Array.from({ length: groupId }, (_, i) => i);

  function find(x) {
    if (parent[x] !== x) parent[x] = find(parent[x]);
    return parent[x];
  }

  function union(a, b) {
    const pa = find(a);
    const pb = find(b);

    if (pa === pb) return false;

    parent[pb] = pa;
    return true;
  }

  let answer = 0;
  let connected = 0;

  for (const [cost, a, b] of edges) {
    if (union(a, b)) {
      answer += cost;
      connected++;

      if (connected === groupId - 1) break;
    }
  }

  return answer;
}